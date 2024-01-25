module ProjectBloodDonation {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens donormasWala to javafx.graphics, javafx.fxml;
	opens bloodunit to javafx.graphics, javafx.fxml;
	opens bloodAvailable to javafx.graphics, javafx.fxml;
	opens issueBlood to javafx.graphics,javafx.fxml;
	opens loginpack to javafx.graphics,javafx.fxml;
	opens controlpanel to javafx.graphics,javafx.fxml;
	opens practiceTableView to javafx.graphics,javafx.fxml,javafx.base;
	opens projectTableView to javafx.graphics,javafx.fxml,javafx.base;
	opens donorCollectionTbl to javafx.graphics,javafx.fxml,javafx.base;
	opens bloodIssueTable to javafx.graphics,javafx.fxml,javafx.base;
}
