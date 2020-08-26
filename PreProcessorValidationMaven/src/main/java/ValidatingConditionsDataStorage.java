import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ValidatingConditionsDataStorage {
	// TODO MAKE THESE UNMODIFIABLE

	// Creating Map having all list of all header fields to be validated in static
	// block
	public static final Map<String, String> headersMap = new HashMap<String, String>() {
		{
			put("SERVICE_DATE", "serviceDateValidator");
			put("CUSTOMER_PAYMENT_DATE", "customerPaymentDateValidator");
			put("TERM_MONTHLY", "termMonthlyValidator");
			put("COMP_TYPE", "compTypeValidator");
			put("RATE_RETAIL", "rateRetailValidator");
			put("RATE_WHSL_MASTER_AGENT", "rateWHSLMasterAgentValidator");
			put("RATE_WHSL_AGENT", "rateWHSLAgentValidator");
			put("REVENUE", "revenueValidator");
			put("ON_HOLD_REVENUE", "onHoldRevenue");
			put("SALE_DATE", "saleDateValidator");
			put("INSTALL_DATE", "installDateValidator");
			put("CONTRACT_END_DATE", "contractEndDateValidator");
			put("PROVIDER_DISCO_DATE", "providerDiscoDateValidator");
			put("ACTUAL_STM_COMP_RATE", "actualSTMCompRateValidator");
			put("REPORT_COMP", "reportCompValidator");
			put("MODIFIED_BY", "modifiedByValidator");
			put("MODIFIED_DATE", "modifiedDateValidator");
			put("CASE_ID", "caseIdValidator");
			put("OFFICE_CONTRACT", "officeContractValidator");
		}
	};
	public static final HashSet<String> compTypeSet = new HashSet<String>() {
		{
			add("RESIDUAL");
			add("UPFRONT");
			add("SPIFF");
			add("BUYRATE");
			add("UNITS");
		}
	};
	
	
}
