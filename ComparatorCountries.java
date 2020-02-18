package comparatorInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


//This is forked to the Wadila project
//compares countries by country id and country name

class Country implements Comparator<Country>{
    int countryId;
    String countryName;
    
    public Country(int countryId, String countryName) {
        super();
        this.countryId = countryId;
        this.countryName = countryName;
    }
    
    /*
@return list of countries by country id
*/
  /*

    @return country id

    */
    public int getCountryId() {
        return countryId;
    }
    
    /*
set the country id
    */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    
    
    public String getCountryName() {
        return countryName;
    }
    
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
/*
 @return countries by highest id number
 * */
        public int compare(Country country1, Country country2) {
            
            return (country1.getCountryId() < country2.getCountryId() ) ? -1: (country1.getCountryId() > country2.getCountryId() ) ? 1:0 ;
        }
}
        
     public class ComparatorCountries{
      public static void main(String[] args) {
    	  
    	  //Country class
            Country indiaCountry=new Country(1, "India");
            Country usaCountry=new Country(3, "USA");
            Country russiaCountry=new Country(4, "Russia");
            Country japanCountry=new Country(2, "Japan");
            
            //list interface: ArrayList
            List<Country> listOfCountries = new ArrayList<Country>();
            listOfCountries.add(indiaCountry);
            listOfCountries.add(usaCountry);
            listOfCountries.add(russiaCountry);
            listOfCountries.add(japanCountry);
            
            System.out.println("Before Sorted by id : ");
            for (int i = 0; i < listOfCountries.size(); i++) {
                Country country=(Country) listOfCountries.get(i);
                System.out.println("Country Id: "+country.getCountryId()+"||"+"Country name: "+country.getCountryName());
            }
            Collections.sort(listOfCountries, new Country(0, ""));
            
            System.out.println("After Sorted by id: ");
            for (int i = 0; i < listOfCountries.size(); i++) {
                Country country = (Country) listOfCountries.get(i);
                System.out.println("Country Id: "+country.getCountryId()+"|| "+"Country name: "+country.getCountryName());
            }
            
            //Sort by countryName
            Collections.sort(listOfCountries, new Comparator<Country>() {
                
                @Override
                public int compare(Country o1, Country o2) {
                    return o1.getCountryName().compareTo(o2.getCountryName());
                }
            });
            
            System.out.println("After Sort by name: ");
            for (int i = 0; i < listOfCountries.size(); i++) {
                Country country=(Country) listOfCountries.get(i);
                System.out.println("Country Id: "+country.getCountryId()+"|| "+"Country name: "+country.getCountryName());
            }
            
        }
        
}
  
