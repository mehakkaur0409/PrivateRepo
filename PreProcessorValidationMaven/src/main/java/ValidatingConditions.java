import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidatingConditions {

	private static String compType;

	public boolean spaceValidator(String cellValue) {


		return (!cellValue.contains(" "));
	}

	public boolean compTypeValidator(String number, String compType) {

		if (compType.equals("BUYRATE")) {
			if (ValidatingConditions.compType.equals(compType)) {
				return (!number.isEmpty() && number.matches("^[0-9]+(?:\\.[0-9]{0,4})?$"));
			} else {
				return number.isEmpty();
			}
		} else if (compType.equals("RESIDUAL")) {
			if (ValidatingConditions.compType.equals(compType)) {
				return (!number.isEmpty() && number.matches("^[-]?[0-9]+(?:\\.[0-9]{0,2})?$"));
			} else {
				return number.isEmpty();
			}
		} else
			return false;

	}

	public boolean isValidDate(String dateStr, String dateFormat) {
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public boolean serviceDateValidator(String serviceDate) {
		/* YYYY-MM / Text */
		// isValidDate(serviceDate, "YYYY-MM");
		/* can't be higher than AppSmart cycle */
//        LocalDate currentdate = LocalDate.now();
//        String currentAppsmartCycle=   currentdate.getYear()+"-"+currentdate.getMonthValue();
		if (serviceDate != null && serviceDate.equals(""))
			return isValidDate(serviceDate, "YYYY-MM");
		else
			return true;
	}

	public boolean customerPaymentDateValidator(String customerPaymentDate) {
		/*
		 * MM/DD/YYYY (Short Date Format), date value
		 */
		if (customerPaymentDate != null && !customerPaymentDate.equals(""))
			return isValidDate(customerPaymentDate, "MM/DD/YYYY");
		else
			return true;
	}

	public boolean termMonthlyValidator(String number) {
		/*
		 * Number; No decimals, Nothing more than a 10 year contract
		 */
		if (number != null && !number.equals("")) {
			if (Integer.valueOf(number) < 120)
				return number.matches("^[0-9]*");
			else
				return false;
		} else
			return true;

	}

	public boolean compTypeValidator(String compType) {
		/*
		 * Text, It can not be blank; predetermined values (RESIDUAL, UPFRONT, SPIFF,
		 * BUY RATE or UNITS)
		 */
		ValidatingConditions.compType = compType;
		if (compType.isEmpty() || !ValidatingConditionsDataStorage.compTypeSet.contains(compType))
			return false;
		return true;

	}

	public boolean rateRetailValidator(String number) {
		/*
		 * Number format; round 4 places, It can't be blank if the Comp Type is BUYRATE
		 */

		// if comp_type = BUY RATE then RATE_WHSL_AGENT must be populated. if comp_type
		// != BUY RATE, then RATE_WHSL_AGENT must not be populated.

		return compTypeValidator(number, "BUYRATE");

	}

	public boolean rateWHSLMasterAgentValidator(String number) {
		/*
		 * Number format; round 4 places, Populate only if the Comp Type is BUYRATE; it
		 * can not be blank if Comp Type is Buyrate
		 */
		return compTypeValidator(number, "BUYRATE");

	}

	public boolean rateWHSLAgentValidator(String number) {
		/*
		 * Number format; round 4 places, Populate only if the Comp Type is BUYRATE; it
		 * can not be blank if Comp Type is Buyrate
		 */
		return compTypeValidator(number, "BUYRATE");
	}

	public boolean revenueValidator(String revenue) {
		/*
		 * Accounting Format $; Round 2 places Populate only if the Comp Type is
		 * RESIDUAL; it can not be blank if Comp Type is Residual
		 */
		return compTypeValidator(revenue, "RESIDUAL");

	}

	public boolean onHoldRevenue(String onHoldRevenue) {
		/*
		 * Accounting Format $; Round 2 places Populate only if the Comp Type is
		 * RESIDUAL; it can not be blank if Comp Type is Residual
		 */
		return compTypeValidator(onHoldRevenue, "RESIDUAL");

	}

	public boolean saleDateValidator(String saleDate) {
		/*
		 * MM/DD/YYYY (Short Date Format) Date value
		 */
		if (saleDate != null && !saleDate.equals(""))
			return isValidDate(saleDate, "MM/DD/YYYY");
		else
			return true;
	}

	public boolean installDateValidator(String installDate) {
		/*
		 * MM/DD/YYYY (Short Date Format) Date value
		 */
		if (installDate != null && !installDate.equals(""))
			return isValidDate(installDate, "MM/DD/YYYY");
		else
			return true;
	}

	public boolean contractEndDateValidator(String contractEndDate) {
		/*
		 * MM/DD/YYYY (Short Date Format) Date value
		 */
		if (contractEndDate != null && !contractEndDate.equals(""))
			return isValidDate(contractEndDate, "MM/DD/YYYY");
		else
			return true;
	}

	public boolean providerDiscoDateValidator(String providerDiscoDate) {
		/*
		 * MM/DD/YYYY (Short Date Format) Date value
		 */
		if (providerDiscoDate != null && !providerDiscoDate.equals(""))
			return isValidDate(providerDiscoDate, "MM/DD/YYYY");
		else
			return true;
	}

	public boolean actualSTMCompRateValidator(String number) {
		/*
		 * Percentage Format; Round 6 places; show 4 digits after the decimal point
		 * Populate only if the Comp Type is RESIDUAL; it can not be blank if Comp Type
		 * is Residual
		 */

		if (ValidatingConditions.compType.equals("RESIDUAL")) {
			return (!number.isEmpty() && number.matches("^([0-9]{0,2})+(?:\\.[0-9]{0,4})?%"));
		} else {
			return number.isEmpty();
		}

	}

	public boolean reportCompValidator(String number) {
		/*
		 * Accounting Format $; Round 2 places Can't be blank
		 */
		return (!number.isEmpty() && number.matches("^\\$[-]?[0-9]+(?:\\.[0-9]{0,2})?$"));

	}

	public boolean modifiedByValidator(String text) {
		/*
		 * Text Can't be blank
		 */
		return (!text.isEmpty());
	}

	public boolean modifiedDateValidator(String modifiedDate) {
		/*
		 * MM/DD/YYYY (Short Date Format) Date value
		 */
		if (modifiedDate != null && !modifiedDate.equals(""))
			return isValidDate(modifiedDate, "MM/DD/YYYY");
		else
			return true;
	}

	public boolean caseIdValidator(String caseId) {
		/* Validate Number format */

		if (caseId != null && !caseId.equals(""))
			return caseId.matches("^[0-9]*");
		else
			return true;
	}

	public boolean officeContractValidator(String text) {
		/* Can't be blank */
		return (!text.isEmpty());
	}

}
