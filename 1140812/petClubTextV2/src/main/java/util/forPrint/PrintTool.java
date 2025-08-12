package util.forPrint;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class PrintTool {

	public static void main(String[] args) {

	}
	
	/**列印起始畫面**/
	

	/**報表尋找*/
	 public static String[] getAvailableReports() {
	        File folder = new File("reporter"); 
	        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt") || name.endsWith(".xls"));
	        if (files == null) {
	            return new String[0]; // 或丟出例外、寫入 log
	        }
	        // 取得檔案名稱並轉換為字符串陣列
	        String[] reportNames = new String[files.length];
	        for (int i = 0; i < files.length; i++) {
	            reportNames[i] = files[i].getName(); // 只取得檔案名稱
	        }
	        return reportNames;
	    }

	 /**txt選擇**/
	 public static void chooseAndPrintTxt() throws Exception {
		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setDialogTitle("選擇 TXT 檔案");
		    fileChooser.setFileFilter(new FileNameExtensionFilter("文字檔案 (*.txt)", "txt"));

		    int result = fileChooser.showOpenDialog(null);
		    if (result == JFileChooser.APPROVE_OPTION) {
		        File selectedFile = fileChooser.getSelectedFile();
		        String content = new String(java.nio.file.Files.readAllBytes(selectedFile.toPath()));
		        boolean confirmed = PrintTool.previewReport(selectedFile.getAbsolutePath());
		        if (confirmed) {
		        	PrintTool.printText(content);
		        } else {
		            JOptionPane.showMessageDialog(null, "使用者取消列印。");
		        }
		    } else {
		        JOptionPane.showMessageDialog(null, "未選取任何 TXT 檔案。");
		    }
		}

	 /**xls選擇**/	 
	 public static void chooseAndPrintReport() throws Exception {
		    String[] reports = PrintTool.getAvailableReports();

		    if (reports.length == 0) {
		        JOptionPane.showMessageDialog(null, "沒有可用的報表檔案");
		        return;
		    }

		    String selectedReport = (String) JOptionPane.showInputDialog(
		            null,
		            "請選擇報表檔案",
		            "報表清單",
		            JOptionPane.PLAIN_MESSAGE,
		            null,
		            reports,
		            reports[0]
		    );

		    if (selectedReport == null) {
		        JOptionPane.showMessageDialog(null, "未選擇報表，操作取消");
		        return;
		    }

		    File file = new File("repoter", selectedReport);

		    if (!file.exists()) {
		        JOptionPane.showMessageDialog(null, "檔案不存在：" + file.getAbsolutePath());
		        return;
		    }

		    if (!previewReport(file.getAbsolutePath())) {
		        JOptionPane.showMessageDialog(null, "使用者取消列印。");
		        return;
		    }

		    PrintTool.printExcelAsTable(file.getAbsolutePath());
		}

	
	/**列印前先顯示是否確認**/
	public static boolean previewReport(String filePath) throws Exception {
	    File file = new File(filePath);
	    if (filePath.endsWith(".txt")) {
	        String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
	        int res = JOptionPane.showConfirmDialog(null, content, "預覽文字報表", JOptionPane.OK_CANCEL_OPTION);
	        return res == JOptionPane.OK_OPTION;
	    } else if (filePath.endsWith(".xls")) {
	        FileInputStream fis = new FileInputStream(file);
	        Workbook workbook = new HSSFWorkbook(fis);
	        Sheet sheet = workbook.getSheetAt(0);
	        StringBuilder previewBuilder = new StringBuilder();

	        int minRow = Integer.MAX_VALUE, maxRow = Integer.MIN_VALUE;
	        int minCol = Integer.MAX_VALUE, maxCol = Integer.MIN_VALUE;

	        for (int r = 0; r <= sheet.getLastRowNum(); r++) {
	            Row row = sheet.getRow(r);
	            if (row == null) continue;
	            for (int c = 0; c < row.getLastCellNum(); c++) {
	                Cell cell = row.getCell(c);
	                if (cell != null && !cell.toString().trim().isEmpty()) {
	                    minRow = Math.min(minRow, r);
	                    maxRow = Math.max(maxRow, r);
	                    minCol = Math.min(minCol, c);
	                    maxCol = Math.max(maxCol, c);
	                }
	            }
	        }
	        if (minRow == Integer.MAX_VALUE) {
	            JOptionPane.showMessageDialog(null, "報表中無資料可預覽");
	            return false;
	        }

	        for (int r = minRow; r <= maxRow; r++) {
	            Row row = sheet.getRow(r);
	            if (row == null) {
	                previewBuilder.append("\n");
	                continue;
	            }
	            for (int c = minCol; c <= maxCol; c++) {
	                Cell cell = row.getCell(c);
	                String val = (cell == null) ? "" : cell.toString();
	                previewBuilder.append(val).append("\t");
	            }
	            previewBuilder.append("\n");
	        }

	        fis.close();

	        int res = JOptionPane.showConfirmDialog(null, previewBuilder.toString(), "預覽Excel報表", JOptionPane.OK_CANCEL_OPTION);
	        return res == JOptionPane.OK_OPTION;
	    } else {
	        JOptionPane.showMessageDialog(null, "不支援的預覽格式");
	        return false;
	    }
	}
	
	
	/**txt的列印範圍設定*/
	public static void printText(String content) throws Exception {
	    PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintable((g, pf, pageIndex) -> {
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.translate(pf.getImageableX(), pf.getImageableY());
	        Font font = new Font("Serif", Font.PLAIN, 12);
	        g2d.setFont(font);
	        FontMetrics metrics = g2d.getFontMetrics();
	        int lineHeight = metrics.getHeight();

	        String[] lines = content.split("\n");
	        int linesPerPage = (int) (pf.getImageableHeight() / lineHeight);
	        int start = pageIndex * linesPerPage;
	        if (start >= lines.length) {
	            return Printable.NO_SUCH_PAGE;
	        }

	        int y = 0;
	        for (int i = start; i < Math.min(start + linesPerPage, lines.length); i++) {
	            y += lineHeight;
	            g2d.drawString(lines[i], 0, y);
	        }

	        return Printable.PAGE_EXISTS;
	    });

	    if (job.printDialog()) {
	        job.print();
	    }
	}

	
	/**excel的列印設定**/
	public static void printExcelAsTable(String filePath) throws Exception {
	    FileInputStream fis = new FileInputStream(filePath);
	    Workbook workbook = new HSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheetAt(0);

	    int minRow = Integer.MAX_VALUE, maxRow = Integer.MIN_VALUE;
	    int minCol = Integer.MAX_VALUE, maxCol = Integer.MIN_VALUE;

	    // 偵測有內容的區域
	    for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	        Row row = sheet.getRow(rowIndex);
	        if (row == null)
	            continue;

	        for (int colIndex = 0; colIndex < row.getLastCellNum(); colIndex++) {
	            Cell cell = row.getCell(colIndex);
	            if (cell != null && !cell.toString().trim().isEmpty()) {
	                minRow = Math.min(minRow, rowIndex);
	                maxRow = Math.max(maxRow, rowIndex);
	                minCol = Math.min(minCol, colIndex);
	                maxCol = Math.max(maxCol, colIndex);
	            }
	        }
	    }

	    if (minRow == Integer.MAX_VALUE) {
	        JOptionPane.showMessageDialog(null, "報表中無資料可列印");
	        return;
	    }

	    // 匿名類別內使用的變數需是final或effectively final
	    final int fMinRow = minRow;
	    final int fMaxRow = maxRow;
	    final int fMinCol = minCol;
	    final int fMaxCol = maxCol;

	    /**excel列印範圍設定**/
	    PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintable(new Printable() {
	        @Override
	        public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
	            if (pageIndex > 0)
	                return NO_SUCH_PAGE;

	            Graphics2D g2d = (Graphics2D) g;
	            g2d.translate(pf.getImageableX(), pf.getImageableY());

	            Font font = new Font("SansSerif", Font.PLAIN, 10);
	            g2d.setFont(font);
	            FontMetrics metrics = g2d.getFontMetrics();

	            int padding = 10;

	            int colCount = fMaxCol - fMinCol + 1;
	            int rowCount = fMaxRow - fMinRow + 1;
	            int[] colWidths = new int[colCount];
	            int rowHeight = metrics.getHeight() + padding;

	            // 計算每一欄寬度
	            for (int rowIndex = fMinRow; rowIndex <= fMaxRow; rowIndex++) {
	                Row row = sheet.getRow(rowIndex);
	                if (row == null)
	                    continue;

	                for (int colIndex = fMinCol; colIndex <= fMaxCol; colIndex++) {
	                    Cell cell = row.getCell(colIndex);
	                    String value = (cell != null) ? cell.toString() : "";
	                    int colWidth = metrics.stringWidth(value) + padding * 2;

	                    int realCol = colIndex - fMinCol;
	                    colWidths[realCol] = Math.max(colWidths[realCol], colWidth);
	                }
	            }

	            // 開始畫表格
	            int x = 0, y = 0;

	            for (int rowIndex = fMinRow; rowIndex <= fMaxRow; rowIndex++) {
	                Row row = sheet.getRow(rowIndex);
	                if (row == null)
	                    continue;
	                x = 0;
	                for (int colIndex = fMinCol; colIndex <= fMaxCol; colIndex++) {
	                    Cell cell = row.getCell(colIndex);
	                    String value = (cell != null) ? cell.toString() : "";
	                    int colOffset = colIndex - fMinCol;
	                    int width = colWidths[colOffset];
	                    // 畫格線
	                    g2d.drawRect(x, y, width, rowHeight);
	                    // 畫文字
	                    g2d.drawString(value, x + padding, y + metrics.getAscent() + padding / 2);
	                    x += width;
	                }
	                y += rowHeight;
	            }
	            return PAGE_EXISTS;
	        }
	    });
	    // 顯示列印對話框並開始列印
	    if (job.printDialog()) {
	        job.print();
	    }
	}


}
