package TestPackage;
import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;
import BaseClassPackage.CourseraBase;
import PagePackage.CourseraProgram;
public class CourseraTest extends CourseraBase 
{
  @Test
  public void testCoursera() throws AWTException, InterruptedException, IOException
  {
	  CourseraProgram obj = new CourseraProgram(driver);
      obj.MouseHoverClick();
      obj.ScrollDownClick();
      obj.WindowHandleClick();
      obj.SetLoginValues("ahsanakareem258@gmail.com", "Passpass123@#");
      obj.CopyAndPaste();
      obj.FileUpload("C:\\Users\\ahsan\\OneDrive\\Documents\\Resume\\copy.pdf");
      obj.DataDrivenTest("C:\\Users\\ahsan\\OneDrive\\Documents\\Book2.xlsx");
      obj.DropDownHandling();
	  obj.DragAndDrop();
	  obj.LinkCount();
	  obj.PageSource();
	  obj.ScreenShot();
  }
}






