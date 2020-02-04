package edu.century.pa1;
/**
 * @author Samuel Bedell
 * Class PayCalculator 
 * Makes an object to calculate ones pay based on hours and
 * wages and can calculate net pay and gross pay.
 */

public class PayCalculator implements Constants	
	private String employeeName;
	private int reportId;
	private double hourlyWage;
	private int[] payPeriods=null;;
	private static int idCount=1000;
	
	/**
	 *Specification: Default Constructor that sets name to "John Doe" and
	 *hourlyWage to minimum wage.
	 *@param none
	 *@PostCondition initialize values for the payCalculator class with john doe as the name
	 *and 7.75 as the starting calculator.
	 */
	public PayCalculator(){
		employeeName="John Doe";
		hourlyWage=7.75;
		reportId=idCount;
		idCount+=10;
	}
	
	/**
	 *Specification Constructor that takes a name and starting salary and makes a new object.
	 *@param name-Employees name
	 *@param hourlyWage-Starting salary for employee
	 *PreCondition Hourly wage must be positive and name must not be an empty string. 
	 *@throws if either precondition not met throws exception.
	 */
	public PayCalculator(String name,double hourlyWage){
		employeeName=name;
		this.hourlyWage=hourlyWage;
		reportId=idCount;
		idCount+=10;
	}

	/**
	 *Specification GetEmployeeName:returns the name of the employee. 
	 *@return employeeName-name of employee;
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 *Specification SetEmployeeName:Modifies Employees name
	 *@param employeeName-Takes a string as an argument to change the name.
	 *@PreCondition employeeName must not be an empty String
	 *@throws throws if employeeName is an empty String.
	 */
	public void setEmployeeName(String name) throws Exception{
		if (name.equals("")) {
			throw new Exception("Name cannot be blank");
		}
		this.employeeName = name;
	}

	/**
	 *Specification getHourlyWage: Returns the hourlyWage for employee. 
	 *@return hourlyWage-Returns the hourly wages as a double.
	 */
	public double getHourlyWage() {
		return hourlyWage;
	}

	/**
	 *Specification setHourlyWage:Updates or changes the hourlyWage of the employee
	 *@param hourlyWage-A double arguments that will replace the hourlyWage
	 *PreCondition hourlyWage must be a positive number
	 *@return none
	 *@throws any negative numbers.
	 */
	public void setHourlyWage(double hourlyWage) {
		if (hourlyWage<0) {
			System.out.println("HourlyWage cannot be less than 0");
			return;
		}
		this.hourlyWage = hourlyWage;
	}
	
	/**
	 *Specification setReportId: Updates or changes the repordId of the employee
	 *@param id-An int arguments that will replace the current id.
	 *PreCondition none
	 */
	public void setReportId(int id) {
		this.reportId=id;
	}
	
	/**
	 *Specification getReportId(): returns the id of the employee.
	 *@return reportId: the id of the employee. 
	 */
	public int getReportId() {
		return this.reportId;
	}
		
	/**
	 * overTimeHoursWorked()
	 * gets the total amount of over times hours worked
	 * @param hours - the amount of hours total worked
	 * @return overtime- the number of over time hours worked.
	 */
	public double overTimeHoursWorked(double hours) {
		double overTime=hours-FULL_TIME;
		return overTime;
	}
	
	/**
	 * overTimePay() gets the amount of over time pay.
	 * @param hours- the over time hours that will be calculated
	 * @return pay - the amount of money from overtime.
	 */
	public double overTimePay(double hours) {
		double overTimeWage = hourlyWage*OVERTIME_RATE;
		double pay = hours * overTimeWage;
		return pay;
	}
	
	/**
	 *Specification grossYearIncome: computers and returns gross pay for a year(26 pay periods).
	 *@param payPeriodHours- an array of hours that will be calculated
	 *@return grossPay-gross pay for a year
	 */
	public double grossYearIncome(int[] payPeriodHours) {
		double grossPay=0;
		for (int count=0;count<payPeriodHours.length;count++){
			grossPay+=grossPay(payPeriodHours[count]);
		}
		return grossPay;
	}
	
	/**
	 *Specification netYearIncome: computes and returns the gross pay.
	 *@param PayPeriodHours - an array of hours that have been worked.
	 *@return (netPay-taxTaken)-the amount of pay after tax is taken out.
	 */
	public double netYearIncome(int[] PayPeriodHours) {
		double netPayYear=0;
		for (int count=0;count<PayPeriodHours.length;count++) {
			netPayYear+=netPay(PayPeriodHours[count]);
		}
		return netPayYear;
	}

	/**
	 *Specification grossPay-Calculates the pay for a given pay period 
	 *@param double hours-the amount of hours worked
	 *@return payForPeriod-returns the amount of pay for a given period.
	 */
	public double grossPay(double hours) {
		double pay=0;
		if (hours>FULL_TIME) {
			pay+=overTimePay(overTimeHoursWorked(hours));
			hours-=overTimeHoursWorked(hours);
		}
		pay+=hours*hourlyWage;
		return pay;
	}
	
	/**
	 *Specification netPay-Calculates the netPay for an amount of hours
	 *@param hoursWorked-argument for the number of hours worked
	 *@return pay-returns the amount of pay for the amount of hours worked.
	 */
	public double netPay(double hours) {
		double pay=grossPay(hours);
		double fedDeduc=federalDeductions(pay);
		double stateDeduc=stateDeductions(pay);
		pay-=totalDeductions(fedDeduc,stateDeduc);
		return pay;
	}
	
	/**
	 *Specification stateDeduction()-Calculates the amount taken from the state. 
	 *@param amount-amount of pay before taxes
	 *@return deduction - amount taken out from taxes
	 */
	public double stateDeductions(double amount) {
		double deduction = (amount*STATE_TAX_RATE);
		return deduction;
	}
	
	/**
	 *Specification fedDeduction()-Calculates the amount taken from the fed. 
	 *@param pay-amount of pay before taxes/
	 *@return deduction - amount taken out from taxes
	 */
	public double federalDeductions(double amount) {
		double deduction = (amount*FEDERAL_TAX_RATE);
		return deduction;
	}
	
	/**
	 * totalDeductions-calculates the total amount taken from the fed and state.
	 * @param fedAmount
	 * @param stateAmount
	 * @return totalDeductions - the total amount taken from taxes.
	 */
	public double totalDeductions(double fedAmount,double stateAmount) {
		double totalDeduction = fedAmount+stateAmount;
		return totalDeduction;
	}
	
	/**
	 *Specification: increaseHourlyWage() Raises the hourly wage of an employee by a percentage.
	 *@param raisePercent-Percentage to raise the employees wages
	 *PreCondition raisePercentage must be positive
	 *@return none
	 *@throws if percentage is negative will throw invalidArgumentException.
	 */
	public void increaseHourlyWage(double raisePercent) {
		if (raisePercent<0) {
			System.out.println("Raise cannot be negative");
			return;
		}	
		raisePercent=((raisePercent/100)+1);
		this.hourlyWage=(raisePercent)*hourlyWage;
	}
	
	/**
	 *Specification ToString-puts all the important information about this object
	 * and returns it as a string.
	 * @return info-All the important information about the employee.
	 * @throws none.
	 */
	public String toString(){
		String info;
		try {
		info="Name: "+ employeeName+"\nEmployee ID: "+ reportId+ "\nHourly wages: "+ hourlyWage+"\nGross Yearly Pay: " +
		grossYearIncome(HOURS_WORKED)+"\nNet Yearly Pay: "+ netYearIncome(HOURS_WORKED)+"\n";
		}
		catch (Exception e) {
			info=e.getMessage();
		}
		return info;
	}
}
