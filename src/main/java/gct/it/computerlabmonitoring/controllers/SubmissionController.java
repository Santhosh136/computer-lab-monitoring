package gct.it.computerlabmonitoring.controllers;

import java.io.ByteArrayOutputStream;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gct.it.computerlabmonitoring.entities.ExpDocument;
import gct.it.computerlabmonitoring.entities.Submission;
import gct.it.computerlabmonitoring.repositories.ExpDocumentRepo;
import gct.it.computerlabmonitoring.repositories.ExperimentRepo;
import gct.it.computerlabmonitoring.repositories.StudentRepo;
import gct.it.computerlabmonitoring.repositories.SubmissionRepo;

// Controller class for Experiment Submission
@Controller @RequestMapping("/submissions")
public class SubmissionController {
    @Autowired
    private ExpDocumentRepo docRepo;

    @Autowired
    private ExperimentRepo expRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SubmissionRepo submissionRepo;

    @GetMapping
    public String allExps(Model model) {
        model.addAttribute("docs", docRepo.findAll());
        return "submission/submission-list";
    }

    @GetMapping("/new")
    public String newSubmission(@Param("id") Integer id, Principal principal, Model model) {
        Submission submission = new Submission();
        submission.setExp(expRepo.findById(id).get());
        submission.setStudent(studentRepo.findById(principal.getName()).get());

        submissionRepo.save(submission);

        model.addAttribute("subId", submission.getSubmissionId());
        model.addAttribute("expId", id);
        return "submission/submission-new";
    }

    @GetMapping("/download")
    public void downloadExp(@Param("id") Integer id, HttpServletResponse response ) throws Exception {

        Optional<ExpDocument> result = docRepo.findById(id);

        if(!result.isPresent()) throw new Exception("Download failed");
        ExpDocument doc = result.get();
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename="+ doc.getFileName());
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(doc.getContent());
        outputStream.close();
    }

    private void addParagraph(Document doc, String name, String val, Font boldFont, Font normalFont) {
        Paragraph p = new Paragraph();
        p.add(new Chunk(name, boldFont));
        p.add(new Chunk(val, normalFont));
        try { doc.add(p); } 
        catch (DocumentException e) { e.printStackTrace(); }
    }

    @PostMapping("/save")
    public String saveExp(
        @RequestParam("code") String code, 
        @RequestParam("op") String output,
        @RequestParam("language") String language, 
        @RequestParam("subId") Integer subId,
        @RequestParam("expId") Integer expId) throws Exception {
        // setting up fontStyles
        Font codeFont = FontFactory.getFont(FontFactory.COURIER, 12);
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Getting submission
        Optional<Submission> result = submissionRepo.findById(subId);

        if(!result.isPresent()) throw new Exception("No submission");
        Submission submission  = result.get();

        String courseCode = submission.getExp().getCourse().getCode();
        String courseName = submission.getExp().getCourse().getName();

        String studentName = submission.getStudent().getName();
        String studentRegno = submission.getStudent().getRegNo();
        String expNo = submission.getExp().getExpNo();
        String expTitle = submission.getExp().getTitle();
        String expDesc = submission.getExp().getDescription();
        
        // creating new pdf document 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
        PdfWriter pd = PdfWriter.getInstance(doc, baos);

        // opening document and adding content
        doc.open();

        Paragraph p = new Paragraph();
        p.add(new Chunk(courseCode+" - ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15)));
        p.add(new Chunk(courseName, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15)));
        p.setAlignment(Element.ALIGN_CENTER);
        doc.add(p);
        doc.add( Chunk.NEWLINE);

        addParagraph(doc, "Student Regno:", studentRegno, boldFont, normalFont);
        addParagraph(doc, "Student Name:", studentName, boldFont, normalFont);
        addParagraph(doc, "Exp No:", expNo, boldFont, normalFont);
        addParagraph(doc, "Exp Title:", expTitle, boldFont, normalFont);
        addParagraph(doc, "Exp Description:", expDesc, boldFont, normalFont);
        
        doc.add( Chunk.NEWLINE);
        doc.add(new Chunk(new DottedLineSeparator()));

        doc.add(new Paragraph("Source code", FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
        doc.add(new Paragraph(code, codeFont));

        doc.add( Chunk.NEWLINE);
        doc.add( Chunk.NEWLINE);

        doc.add(new Paragraph("Ouput", FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
        doc.add(new Paragraph(output, codeFont));

        // closing resources
        doc.close();
        pd.close();

        ExpDocument expDoc = new ExpDocument();
    
        expDoc.setFileName("Student"+studentRegno+"Exp"+expNo);
        expDoc.setContent(baos.toByteArray());
        expDoc.setSize(Integer.toUnsignedLong(baos.size()));

        docRepo.save(expDoc);
        baos.close();

        // saving expDocument to submission

        submission.setDoc(expDoc);
        submissionRepo.save(submission);

        // redirecting to home
        return "redirect:/students/submissions";
    }
    
}
