package gct.it.computerlabmonitoring.controllers;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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

    // 
    @GetMapping
    public String allExps(Model model) {
        model.addAttribute("docs", docRepo.findAll());
        return "submission-list";
    }

    @GetMapping("/new")
    public String newSubmission(@Param("id") Integer id, Model model) {
        Submission submission = new Submission();
        submission.setExp(expRepo.findById(id).get());
        // change the below code with principal 
        submission.setStudent(studentRepo.findById("136").get());

        submissionRepo.save(submission);

        model.addAttribute("subId", submission.getSubmissionId());
        model.addAttribute("expId", id);
        return "submission-new";
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

    @PostMapping("/save")
    public String saveExp(
        @RequestParam("code") String code, 
        @RequestParam("op") String output,
        @RequestParam("language") String language, 
        @RequestParam("subId") Integer subId,
        @RequestParam("expId") Integer expId) throws Exception {
        // setting up fontStyles
        Font codeFont = FontFactory.getFont(FontFactory.COURIER, 12);

        // creating new pdf document 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
        PdfWriter pd = PdfWriter.getInstance(doc, baos);

        // opening document and adding content
        doc.open();

        doc.add(new Paragraph("Source code", FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
        doc.add(new Paragraph(code, codeFont));
        doc.add(new Paragraph("Ouput", FontFactory.getFont(FontFactory.COURIER_BOLD, 15)));
        doc.add(new Paragraph(output, codeFont));

        // closing resources
        doc.close();
        pd.close();

        // Getting submission
        Optional<Submission> result = submissionRepo.findById(subId);

        if(!result.isPresent()) throw new Exception("No submission");
        Submission submission  = result.get();
        
        ExpDocument expDoc = new ExpDocument();

        String regNo = "136";
        expDoc.setFileName("Student"+regNo+"Exp"+expId);
        expDoc.setContent(baos.toByteArray());
        expDoc.setSize(Integer.toUnsignedLong(baos.size()));

        docRepo.save(expDoc);
        baos.close();

        // saving expDocument to submission

        submission.setDoc(expDoc);
        submissionRepo.save(submission);

        // redirecting to home
        return "redirect:/submissions";
    }
    
}
