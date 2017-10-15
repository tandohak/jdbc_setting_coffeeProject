package kr.or.dgit.jdbc_setting_coffeeProject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import kr.or.dgit.jdbc_setting_coffeeProject.service.DbService;
import kr.or.dgit.jdbc_setting_coffeeProject.service.ExportService;
import kr.or.dgit.jdbc_setting_coffeeProject.service.InitService;
import kr.or.dgit.jdbc_setting_coffeeProject.service.ImportService;
import kr.or.dgit.jdbc_setting_coffeeProject.service.ImportTriggerService;

public class BtnAction extends AbstractAction{
	public BtnAction(String name){
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DbService service = null;
		
		switch (e.getActionCommand()) {
		case "초기화":
			service = InitService.getInstance();
			service.service();
			service = ImportTriggerService.getInstance();
			break;
		case "백업":
			service = ExportService.getInstance();
			break;
		case "복원":
			service = ImportService.getInstance();
			break;
		}
		service.service();
		JOptionPane.showMessageDialog(null, e.getActionCommand() + " 가(이) 완료 되었습니다.");
	}
	
	
}
