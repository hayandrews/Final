package rocket.app.view;


import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)

	ObservableList<String>loanTermList = FXCollections.observableArrayList(
			"15 Years", "30 Years");
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private TextField txtDownPayment;
	@FXML
	private ComboBox<String> cmbTerm;
	@FXML
	private Label lblIncome;
	@FXML
	private Label lblExpenses;
	@FXML
	private Label lblCreditScore;
	@FXML
	private Label lblHouseCost;
	@FXML
	private Label lblloanTerm;
	@FXML
	private Label lblDownPayment;
	@FXML
	private Label lblMortgagePayment;
	@FXML
	private Label lblRate;
	@FXML
	private Button btnCalculatePayment;
	
	@FXML
	private void initialize() {
		cmbTerm.setValue(loanTermList.get(0));
		cmbTerm.setItems(loanTermList);
	}
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		
		//set amount
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		//set expenses
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		//set credit score which will be used to find rate 
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		//set down payment
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		//set income
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		//sets term
		if(cmbTerm.getValue() == "15 Years") {
			lq.setiTerm(15);
		} else {
			lq.setiTerm(30);
		}
		//set rate below, wanted to put exceptions in together
		a.setLoanRequest(lq);
				
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{

		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places

		//Checks if rate is good first, only then will the checking the payment
		//Keeps exceptions together
		double rate = lRequest.getdRate();
		if (rate == 0) {
			lblRate.setText("Your Credit is Invalid");
			lblMortgagePayment.setText("Need valid Credit");
		} else {
			lblRate.setText(Double.toString(rate)+"%");
			
			double pay = lRequest.getdPayment();
			double income = lRequest.getIncome();
			double exp = lRequest.getExpenses();
			
			if ((income * .28) > pay && ((income*.36) - exp > pay)) {
				lblMortgagePayment.setText("$"+String.format("%.2f", lRequest.getdPayment()));
			} else {
				lblMortgagePayment.setText("Too High!");
				}
		}
		
	
		
		
	}
}
