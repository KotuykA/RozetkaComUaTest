Framework is written on JAVA with using of Selenium + TestNG. Maven is used as build tool. Allure is used for reporting. Build runs on CI Jenkins.

Project structure:

src/main/java/ua/com/rozetka/core/BrowserName - enum class for executing WebDriverTestBase (were all the drivers are initialises);
src/main/java/ua/com/rozetka/core/TestListener - class of listeners for the informing that something has happened to a component in the GUI;
src/main/java/ua/com/rozetka/core/WebDriverTestBase - class for all the drivers initialization and set up;

src/main/java/ua/com/rozetka/pages/ - Page Object pattern directory, were:
      AbstractPage - abstract page with common methods for all the other project pages;
      MainPAge, SmartphonesTvElectronic, PhonesPage, SmartphonesPage - particular pages/objects due to Page Object pattern;
      
src/main/java/ua/com/rozetka/PageUtils - class for global project util\variables usage;
src/main/java/ua/com/rozetka/PropertiesCache - class for logging and caching;
src/main/java/ua/com/rozetka/WebDriverUtils - class for web drivers common usages and dif. excellences;

src/test/java/ua/com/rozetka/NCubeTest - supertestclass;

pom.xml - file with Maven configurations;
