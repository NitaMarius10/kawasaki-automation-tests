🏍️ Kawasaki Automation Tests A complete, scalable, and maintainable automation testing framework built from scratch to perform end-to-end testing for the Kawasaki Romania e-commerce platform. It covers critical account lifecycle flows (create account → log in → delete account) and negative scenarios for login validation, ensuring a seamless user experience and high product reliability.

📌 Overview This project demonstrates the ability to build a professional-grade automation framework using industry best practices. It combines modular architecture, advanced reporting, and CI/CD readiness to deliver fast, reliable, and repeatable test execution.

🎯 Main Goal Ensure a flawless user experience while reducing manual testing time, increasing delivery speed, and improving product quality through automation.

🛠 Technologies Used Category Tools & Technologies Language Java 21.0.6 Frameworks Selenium WebDriver, TestNG Design Patterns Page Object Model (POM), Data-Driven Testing (DDT) Build Tool Maven Reporting Allure Reports (with automatic screenshots) IDE IntelliJ IDEA Version Control Git, GitHub CI/CD Jenkins Pipeline (in progress)

🔑 Key Features DriverFactory: Centralized WebDriver management using Singleton Pattern RetryAnalyzer: Automatically re-runs unstable tests ScreenshotUtil: Captures screenshots on failure Allure Listeners: Logs and visual attachments for better traceability External Config: Environment variables managed via .properties files

✅ Test Coverage Test Type Scenarios Covered Functional ✔ Create account, ✔ Login, ✔ Delete account Negative (DDT) ✔ Login with multiple invalid credentials Coverage estimate: ~60–70% of critical account-related business flows ~20% additional coverage for input validation and security Total coverage for tested modules: ~80–85%

🚀 How to Run Prerequisites: Java 21+ Maven Chrome + ChromeDriver Allure installed locally Commands: 

1. Select the location of the project in GitBash

2. rm -rf allure-results allure-report

3. allure generate allure-results --clean -o allure-report   

4. Run tests

mvn clean test -Dtest=tests.CreateAccountTest

   mvn test -Dtest=tests.LoginTest

   mvn test -Dtest=tests.DeleteAccountTest

   mvn test -Dtest=tests.InvalidLoginTest

5. Generate and view Allure report

allure generate target/allure-results --clean -o target/allure-report

allure open target/allure-report

📊 Reporting Type: Allure Reports Location: target/allure-results Screenshots: Automatically captured on failure Benefit: Fast debugging & clear visualization of test results

💡 Skills Demonstrated Skill Area Description Framework Design Built from scratch using OOP and POM principles Data-Driven Testing Implemented with TestNG for flexible test scenarios Visual Reporting Automated Allure integration with screenshots Version Control Git/GitHub usage for collaboration and tracking CI/CD Preparation Jenkins pipeline setup for scalable automation Debugging & Stability RetryAnalyzer for handling flaky tests Real-World Testing E2E and negative test scenarios for production-grade validation

📈 Business Impact & Value This project delivers measurable value in a real-world QA context: 

⏱️ Time Savings: Manual execution of 6–8 test cases: ~20–30 minutes Automated execution: ~2–3 minutes Daily savings (3 runs/day): ~1.4 hours → 30+ hours/month saved per QA 

🔁 Repeatability & Reliability: Tests can be run consistently across environments with minimal setup. 

🐞 Early Defect Detection: Bugs are caught before release, reducing production incidents. 

📊 Clear Documentation: Allure reports provide instant visibility into test results, aiding team communication. 

📦 Scalability: Modular design allows easy expansion to include API testing, mobile automation, and cross-browser support. 

💼 Professionalism: Demonstrates mastery of industry-standard tools and patterns, making it a strong asset in any QA team. 

📸 Screenshots & Examples (Add your own screenshots or GIFs here to visually showcase test execution and reporting)

🙋‍♂️ Why Hire Me Based on This Project

- I built this framework from scratch, not just configured an existing one.
- I applied advanced design patterns and testing strategies used in top-tier QA teams.
- I demonstrated CI/CD thinking, modular architecture, and visual reporting.
- I quantified business impact (time saved, coverage, reliability).
- I showed initiative, scalability, and readiness for real-world challenges.

👤 Author Marius Nita 

📧 Email: nita.marius90@gmail.com

🔗 LinkedIn: www.linkedin.com/in/nita-marius

🖥 GitHub: NitaMarius10
