package gct.it.computerlabmonitoring.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
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
import gct.it.computerlabmonitoring.repositories.ExpDocumentRepo;


@Controller @RequestMapping("/experiments")
public class ExpDocumentController {
    @Autowired
    private ExpDocumentRepo docRepo;

    @GetMapping
    public String allExps(Model model) {
        model.addAttribute("docs", docRepo.findAll());
        return "exp-list";
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
    public String saveExp(@RequestParam("code") String code, @RequestParam("op") String output) throws
    DocumentException, IOException {
        // setting up fontStyles
        Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA ,15 );
        Font codeFont = FontFactory.getFont(FontFactory.COURIER, 12);

        // creating new pdf document 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
        PdfWriter pd = PdfWriter.getInstance(doc, baos);

        // opening document and adding content
        doc.open();

        Chapter chapter1 = new Chapter(new Paragraph("Submission Report\n", chapterFont), 1);
        Section section1 = chapter1.addSection(new Paragraph("Source code\n", titleFont));
        section1.add(new Paragraph(code, codeFont));
        Section section2 = chapter1.addSection(new Paragraph("Output\n", titleFont));
        section2.add(new Paragraph(output, codeFont));
        doc.add(chapter1);

        // closing resources
        doc.close();
        pd.close();

        ExpDocument expDoc = new ExpDocument();

        expDoc.setFileName("exp.pdf");
        expDoc.setContent(baos.toByteArray());
        expDoc.setSize(Integer.toUnsignedLong(baos.size()));

        docRepo.save(expDoc);
        baos.close();

        // redirecting to home
        return "redirect:/";
    }
    
}
