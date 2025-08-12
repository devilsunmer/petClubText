package util.forPrint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import model.CustFreeMember;
import model.CustMember;
import model.CustOrder;
import model.StaffMember;
import service.impl.CustFreeMemberServiceImpl;
import service.impl.CustMemberServiceImpl;
import service.impl.CustOrderServiceImpl;
import service.impl.StaffMemberServiceImpl;

public class ReportDefinition {

	private String[] title;
    private Consumer<ExcelCreat> dataWriter;
    
    
    public ReportDefinition(String[] title, Consumer<ExcelCreat> dataWriter) {
		super();
		this.title = title;
		this.dataWriter = dataWriter;
		
	}
    
    

	public String[] getTitle() {
		return title;
	}

	public Consumer<ExcelCreat> getDataWriter() {
		return dataWriter;
	}

	public static Map<String, ReportDefinition> getReportmap() {
		return reportMap;
	}



	private static final Map<String, ReportDefinition> reportMap = new HashMap<>();

    static {
        reportMap.put("顧客消費訂單", new ReportDefinition(
            new String[]{"訂單編號", "顧客姓名", "預約日期", "預約時長", "消費金額", "會員類型"},
            excel -> {
                List<CustOrder> list = new CustOrderServiceImpl().viewOrder();
                for (CustOrder order : list) {
                    excel.insertCustOrderMoneyValue(order, "顧客消費訂單", "顧客消費訂單");
                }
            }
        ));

        reportMap.put("寵物員工薪資", new ReportDefinition(
            new String[]{"寵物員工編號", "寵物員工名字", "寵物員工賺進金額", "寵物員工獎勵零食"},
            excel -> {
                List<StaffMember> list = new StaffMemberServiceImpl().viewStaff();
                for (StaffMember order : list) {
                    excel.insertStaffMemberValue(order, "寵物員工薪資", "寵物員工薪資");
                }
            }
        ));

        reportMap.put("會員資料", new ReportDefinition(
            new String[]{"顧客會員ID", "顧客會員名稱", "顧客會員帳號", "顧客會員密碼", "顧客電話"},
            excel -> {
                List<CustMember> list = new CustMemberServiceImpl().allMember();
                for (CustMember order : list) {
                    excel.insertCustMemberValue(order, "會員資料", "會員資料");
                }
            }
        ));

        reportMap.put("潛在顧客資料", new ReportDefinition(
            new String[]{"潛在顧客編號", "潛在顧客名稱", "潛在顧客聯繫電話"},
            excel -> {
                List<CustFreeMember> list = new CustFreeMemberServiceImpl().allFreeMember();
                for (CustFreeMember order : list) {
                    excel.insertCustFreeMemberValue(order, "潛在顧客資料", "潛在顧客資料");
                }
            }
        ));

        reportMap.put("寵物員工資料", new ReportDefinition(
            new String[]{"寵物員工編號", "寵物員工名字"},
            excel -> {
                List<StaffMember> list = new StaffMemberServiceImpl().viewStaff();
                for (StaffMember order : list) {
                    excel.insertStaffMemberValue(order, "寵物員工資料", "寵物員工資料");
                }
            }
        ));
    }

    
    
}
