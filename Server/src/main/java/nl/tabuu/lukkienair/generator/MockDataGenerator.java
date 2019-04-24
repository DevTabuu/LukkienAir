package nl.tabuu.lukkienair.generator;

import nl.tabuu.lukkienair.repository.IFlightDestinationRepository;
import nl.tabuu.lukkienair.repository.IFlightRepository;
import nl.tabuu.lukkienair.repository.IFlightRouteRepository;
import nl.tabuu.lukkienair.entity.Flight;
import nl.tabuu.lukkienair.entity.FlightDestination;
import nl.tabuu.lukkienair.entity.FlightRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
public class MockDataGenerator implements ApplicationRunner {

    private Random random;

    @Autowired
    IFlightRepository flightRepository;

    @Autowired
    IFlightRouteRepository flightRouteRepository;

    @Autowired
    IFlightDestinationRepository destinationRepository;



    public MockDataGenerator(){
        random = new Random();
    }

    @Override
    public void run(ApplicationArguments args) {
        List<FlightDestination> destinations = generateDestinations();
        List<FlightRoute> routes = generateRoutes(destinations);
        generateFlights(routes);
    }

    public List<FlightDestination> generateDestinations(){
        List<String> names;
        //region Generation
        names = Arrays.asList("Afghanistan, Kabul",
                "Albania, Tirana",
                "Algeria, Algiers",
                "Andorra, Andorra la Vella",
                "Angola, Luanda",
                "Antigua and Barbuda, Saint John's",
                "Argentina, Buenos Aires",
                "Armenia, Yerevan",
                "Australia, Canberra",
                "Austria, Vienna",
                "Azerbaijan, Baku",
                "Bahamas, Nassau",
                "Bahrain, Manama",
                "Bangladesh, Dhaka",
                "Barbados, Bridgetown",
                "Belarus, Minsk",
                "Belgium, Brussels",
                "Belize, Belmopan",
                "Benin, Porto-Novo",
                "Bhutan, Thimphu",
                "Bolivia, Sucre,",
                "Bosnia and Herzegovina, Sarajevo",
                "Botswana, Gaborone",
                "Brazil, Brasilia",
                "Brunei, Bandar Seri Begawan",
                "Bulgaria, Sofia",
                "Burkina Faso, Ouagadougou",
                "Burundi, Gitega",
                "Cabo Verde, Praia",
                "Cambodia, Phnom Penh",
                "Cameroon, Yaounde",
                "Canada, Ottawa",
                "Central African Republic, Bangui",
                "Chad, N'Djamena",
                "Chile, Santiago",
                "China, Beijing",
                "Colombia, Bogotá",
                "Comoros, Moroni",
                "Congo, Democratic Republic of the, Kinshasa",
                "Congo, Republic of the, Brazzaville",
                "Costa Rica, San Jose",
                "Cote d'Ivoire, Yamoussoukro",
                "Croatia, Zagreb",
                "Cuba, Havana",
                "Cyprus, Nicosia",
                "Czechia, Prague",
                "Denmark, Copenhagen",
                "Djibouti, Djibouti",
                "Dominica, Roseau",
                "Dominican Republic, Santo Domingo",
                "Ecuador, Quito",
                "Egypt, Cairo",
                "El Salvador, San Salvador",
                "Equatorial Guinea, Malabo",
                "Eritrea, Asmara",
                "Estonia, Tallinn",
                "Eswatini, Mbabane,",
                "Ethiopia, Addis Ababa",
                "Fiji, Suva",
                "Finland, Helsinki",
                "France, Paris",
                "Gabon, Libreville",
                "Gambia, Banjul",
                "Georgia, Tbilisi",
                "Germany, Berlin",
                "Ghana, Accra",
                "Greece, Athens",
                "Grenada, Saint George's",
                "Guatemala, Guatemala City",
                "Guinea, Conakry",
                "Guinea-Bissau, Bissau",
                "Guyana, Georgetown",
                "Haiti, Port-au-Prince",
                "Honduras, Tegucigalpa",
                "Hungary, Budapest",
                "Iceland, Reykjavik",
                "India, New Delhi",
                "Indonesia, Jakarta",
                "Iran, Tehran",
                "Iraq, Baghdad",
                "Ireland, Dublin",
                "Israel, Jerusalem",
                "Italy, Rome",
                "Jamaica, Kingston",
                "Japan, Tokyo",
                "Jordan, Amman",
                "Kazakhstan, Nur-Sultan",
                "Kenya, Nairobi",
                "Kiribati, Tarawa",
                "Kosovo, Pristina",
                "Kuwait, Kuwait City",
                "Kyrgyzstan, Bishkek",
                "Laos, Vientiane",
                "Latvia, Riga",
                "Lebanon, Beirut",
                "Lesotho, Maseru",
                "Liberia, Monrovia",
                "Libya, Tripoli",
                "Liechtenstein, Vaduz",
                "Lithuania, Vilnius",
                "Luxembourg, Luxembourg",
                "Madagascar, Antananarivo",
                "Malawi, Lilongwe",
                "Malaysia, Kuala Lumpur",
                "Maldives, Male",
                "Mali, Bamako",
                "Malta, Valletta",
                "Marshall Islands, Majuro",
                "Mauritania, Nouakchott",
                "Mauritius, Port Louis",
                "Mexico, Mexico City",
                "Micronesia, Palikir",
                "Moldova, Chisinau",
                "Monaco, Monaco",
                "Mongolia, Ulaanbaatar",
                "Montenegro, Podgorica",
                "Morocco, Rabat",
                "Mozambique, Maputo",
                "Myanmar, Naypyidaw",
                "Namibia, Windhoek",
                "Nauru, Yaren District",
                "Nepal, Kathmandu",
                "Netherlands, Amsterdam",
                "New Zealand, Wellington",
                "Nicaragua, Managua",
                "Niger, Niamey",
                "Nigeria, Abuja",
                "North Korea, Pyongyang",
                "North Macedonia, Skopje",
                "Norway, Oslo",
                "Oman, Muscat",
                "Pakistan, Islamabad",
                "Palau, Ngerulmud",
                "Palestine, Jerusalem",
                "Panama, Panama City",
                "Papua New Guinea, Port Moresby",
                "Paraguay, Asunción",
                "Peru, Lima",
                "Philippines, Manila",
                "Poland, Warsaw",
                "Portugal, Lisbon",
                "Qatar, Doha",
                "Romania, Bucharest",
                "Russia, Moscow",
                "Rwanda, Kigali",
                "Saint Kitts and Nevis, Basseterre",
                "Saint Lucia, Castries",
                "Saint Vincent and the Grenadines, Kingstown",
                "Samoa, Apia",
                "San Marino, San Marino",
                "Sao Tome and Principe, São Tomé",
                "Saudi Arabia, Riyadh",
                "Senegal, Dakar",
                "Serbia, Belgrade",
                "Seychelles, Victoria",
                "Sierra Leone, Freetown",
                "Singapore, Singapore",
                "Slovakia, Bratislava",
                "Slovenia, Ljubljana",
                "Solomon Islands, Honiara",
                "Somalia, Mogadishu",
                "South Africa, Cape Town",
                "South Korea, Seoul",
                "South Sudan, Juba",
                "Spain, Madrid",
                "Sri Lanka, Sri Jayawardenepura Kotte",
                "Sudan, Khartoum",
                "Suriname, Paramaribo",
                "Sweden, Stockholm",
                "Switzerland, Bern",
                "Syria, Damascus",
                "Taiwan, Taipei",
                "Tajikistan, Dushanbe",
                "Tanzania, Dodoma",
                "Thailand, Bangkok",
                "Timor-Leste, Dili",
                "Togo, Lomé",
                "Tonga, Nukuʻalofa",
                "Trinidad and Tobago, Port of Spain",
                "Tunisia, Tunis",
                "Turkey, Ankara",
                "Turkmenistan, Ashgabat",
                "Tuvalu, Funafuti",
                "Uganda, Kampala",
                "Ukraine, Kyiv",
                "United Arab Emirates, Abu Dhabi",
                "United Kingdom, London",
                "United States of America, Washington, D.C.",
                "Uruguay, Montevideo",
                "Uzbekistan, Tashkent",
                "Vanuatu, Port Vila",
                "Vatican City, Vatican City",
                "Venezuela, Caracas",
                "Vietnam, Hanoi",
                "Yemen, Sana'a",
                "Zambia, Lusaka",
                "Zimbabwe, Harare");
        //endregion

        List<FlightDestination> destinations = names.stream().map(FlightDestination::new).collect(Collectors.toList());
        destinationRepository.saveAll(destinations);

        return destinations;
    }

    public List<FlightRoute> generateRoutes(List<FlightDestination> destinations){
        List<FlightRoute> routes = new ArrayList<>();

        for(FlightDestination from : destinations){
            Collections.shuffle(destinations);

            int amount = random.nextInt(15);

            for(int i = 0; i < amount; i++){
                FlightDestination to = destinations.get(i);

                if(from.equals(to))
                    continue;

                FlightRoute route = new FlightRoute(from, to, random.nextInt(1440));
                routes.add(route);
            }
        }

        flightRouteRepository.saveAll(routes);
        return routes;
    }

    public List<Flight> generateFlights(List<FlightRoute> routes){
        List<Flight> flights = new ArrayList<>();
        DecimalFormat formatter = new DecimalFormat("#.##");
        formatter.setRoundingMode(RoundingMode.CEILING);

        for(FlightRoute route : routes){
            int amount = random.nextInt(10);

            for(int i = 0; i < amount; i++){
                double cost = 100 * random.nextDouble();
                cost = Math.round(cost * 100)/100d;

                long startDate = System.currentTimeMillis() - 86400000L;
                long stopDate = System.currentTimeMillis() + 86400000L;

                Date date = new Date(ThreadLocalRandom.current().nextLong(startDate, stopDate));

                Flight flight = new Flight(route, date, cost);
                flights.add(flight);
            }
        }

        flightRepository.saveAll(flights);
        return flights;
    }
}
