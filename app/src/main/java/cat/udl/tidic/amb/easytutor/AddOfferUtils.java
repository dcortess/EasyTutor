package cat.udl.tidic.amb.easytutor;

public class AddOfferUtils {

    /**
     * This method checks if the provided string represents a valid title and returns true
     * if it is.
     *
     * @param title title
     * @return boolean
     */

    public static boolean isValidTitle(String title){
        if (title.length() > 40){
            return false;
        }
        return true;
    }

    /**
     * This method checks if the provided string represents a valid description and returns true
     * if it is.
     *
     * @param description description of offer
     * @return boolean
     */

    public static boolean isValidDescription(String description){
        if (description.length() > 250){
            return false;
        }
        return true;
    }

    /**
     * This method checks if the provided string represents a reasonable price and returns true
     * if it is.
     *
     * @param price price of offer
     * @return boolean
     */

    public static boolean isReasonablePrice(Float price){
        if (price < 30){
            return false;
        }
        return true;
    }

}
