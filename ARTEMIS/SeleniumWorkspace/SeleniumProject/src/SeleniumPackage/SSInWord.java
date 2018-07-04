package SeleniumPackage;

import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SSInWord extends XWPFDocument {   
	public static boolean CreateSSDoc() {
		try {
			TestAttributes.ExecutionLog = TestAttributes.Global_Test_Id.trim() + " Run " + TestAttributes.Run_ID + " Execution log.docx";
			
			String SSLocation = TestAttributes.LogLocation + TestAttributes.ExecutionLog;
			FileOutputStream out = new FileOutputStream(SSLocation);
			
			XWPFDocument docx = new XWPFDocument();
			
			docx.createParagraph().createRun().setText("Test Execution Log and Screenshots");
			docx.createParagraph().createRun();
			
			XWPFTable table = docx.createTable();
	        
	        XWPFTableRow tableRowOne = table.getRow(0);
	        tableRowOne.getCell(0).setText("Test Id_Name");
	        tableRowOne.addNewTableCell().setText(TestAttributes.Global_Test_Id.trim() + "_" + TestAttributes.Global_Test_ShortName);
	        		        
	        XWPFTableRow tableRowTwo = table.createRow();
	        tableRowTwo.getCell(0).setText("Workflow");
	        tableRowTwo.getCell(1).setText(TestAttributes.Global_Workflow_Name.trim());
	        		        		        
	        XWPFTableRow tableRowThree = table.createRow();
	        tableRowThree.getCell(0).setText("Shared DataSet");
	        tableRowThree.getCell(1).setText(TestAttributes.Global_SharedDataSet);
	        
	        XWPFTableRow tableRowFour = table.createRow();
	        tableRowFour.getCell(0).setText("Test DataSet");
	        tableRowFour.getCell(1).setText(TestAttributes.Global_TestDataSet);
	        
	        XWPFTableRow tableRowFive = table.createRow();
	        tableRowFive.getCell(0).setText("Execution Date & Time");
	        tableRowFive.getCell(1).setText(TestAttributes.Global_CDT);
	        
	        XWPFTableRow tableRowSix = table.createRow();
	        tableRowSix.getCell(0).setText("Test Status");	        
	        tableRowSix.getCell(1).setText(TestAttributes.FinalStatus);
	        
	        docx.createParagraph();
	        
	        docx.write(out); 
	        out.close();	
				       
	        SSInWord document = new SSInWord(new FileInputStream(new File(SSLocation)));
	        FileOutputStream fos = new FileOutputStream(new File(SSLocation));
	           
	        String SSQuery = "Select * from SQS_TEST_RESULT where SQS_TR_Run_Id = " + TestAttributes.Run_ID + " order by SQS_TR_Step_Id";
			
       		ResultSet SSResults = TestAttributes.stmt.executeQuery(SSQuery);
       		
       		while(SSResults.next()) {
       			document.createParagraph();
       			document.createParagraph().createRun().setText("Step Name: " + SSResults.getString("SQS_TR_Sub_Test_Details"));
       			document.createParagraph().createRun().setText("Step Description: " + SSResults.getString("SQS_TR_Step_Description"));
       			document.createParagraph().createRun().setText("Expected Result: " + SSResults.getString("SQS_TR_Expected_Result"));
       			document.createParagraph().createRun().setText("Actual Result: " + SSResults.getString("SQS_TR_Actual_Result"));
       			
       			String SSPaths = SSResults.getString("SQS_TR_ScreenShotPaths");
       			
       			if (!SSPaths.trim().equalsIgnoreCase("")) {
       				String[] SSPathsSplit = SSPaths.trim().split(";", -1);
       				
       				for (int i = 0; i < SSPathsSplit.length; i++){
		       			String id = document.addPictureData(new FileInputStream(new File(SSPathsSplit[i])), Document.PICTURE_TYPE_PNG);        
		       	        document.createPicture(id,document.getNextPicNameNumber(Document.PICTURE_TYPE_PNG), 600, 400);
		       	        document.createParagraph();
       				}
       			}
       		}
       		
	    	document.write(fos);
	        fos.flush();
	        fos.close();
          	       
	       // DeleteSS();
	        return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void DeleteSS() throws SQLException, IOException, InterruptedException {
		String SSQuery = "Select * from SQS_TEST_RESULT where SQS_TR_Run_Id = " + TestAttributes.Run_ID + " order by SQS_TR_Step_Id";
		
   		ResultSet SSResults = TestAttributes.stmt.executeQuery(SSQuery);
   		while (SSResults.next()) {
   			String SSPaths = SSResults.getString("SQS_TR_ScreenShotPaths");
   			
   			if (!SSPaths.trim().equalsIgnoreCase("")) {
   				String[] SSPathsSplit = SSPaths.trim().split(";",-1);
   				
   				for (int i = 0; i < SSPathsSplit.length; i++) {   					
	       			Files.deleteIfExists(Paths.get(SSPathsSplit[i].trim()));
   				}
   			}
   		}
	}
	
    public SSInWord(InputStream in) throws IOException {
        super(in);
    }

    public void createPicture(String blipId,int id, int width, int height) throws XmlException {
        final int EMU = 9525;
        width *= EMU;
        height *= EMU;
        //String blipId = getAllPictures().get(id).getPackageRelationship().getId();

        CTInline inline = createParagraph().createRun().getCTR().addNewDrawing().addNewInline();

        String picXml = "" +
                "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">" +
                "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
                "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
                "         <pic:nvPicPr>" +
                "            <pic:cNvPr id=\"" + id + "\" name=\"Generated\"/>" +
                "            <pic:cNvPicPr/>" +
                "         </pic:nvPicPr>" +
                "         <pic:blipFill>" +
                "            <a:blip r:embed=\"" + blipId + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>" +
                "            <a:stretch>" +
                "               <a:fillRect/>" +
                "            </a:stretch>" +
                "         </pic:blipFill>" +
                "         <pic:spPr>" +
                "            <a:xfrm>" +
                "               <a:off x=\"0\" y=\"0\"/>" +
                "               <a:ext cx=\"" + width + "\" cy=\"" + height + "\"/>" +
                "            </a:xfrm>" +
                "            <a:prstGeom prst=\"rect\">" +
                "               <a:avLst/>" +
                "            </a:prstGeom>" +
                "         </pic:spPr>" +
                "      </pic:pic>" +
                "   </a:graphicData>" +
                "</a:graphic>";

        //CTGraphicalObjectData graphicData = inline.addNewGraphic().addNewGraphicData();
        XmlToken xmlToken = null;
        //try
       // {
            xmlToken = XmlToken.Factory.parse(picXml);
        //}
       // catch(XmlException xe)
        //{
            //xe.printStackTrace();
        //}
        inline.set(xmlToken);
        //graphicData.set(xmlToken);

        inline.setDistT(0);
        inline.setDistB(0);
        inline.setDistL(0);
        inline.setDistR(0);

        CTPositiveSize2D extent = inline.addNewExtent();
        extent.setCx(width);
        extent.setCy(height);

        CTNonVisualDrawingProps docPr = inline.addNewDocPr();
        docPr.setId(id);
        docPr.setName("Picture " + id);
        docPr.setDescr("Generated");
    }
}