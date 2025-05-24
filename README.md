✅ DemoShop practical task

Automation Testing with Selenium, Java, and Cucumber

📄 Overview

This project demonstrates automated web testing using:

Java

Selenium WebDriver

Cucumber BDD (Behavior-Driven Development)

JUnit

Maven

It includes feature files written in Gherkin, step definitions, hooks, and page classes (if used).


🚀 Getting Started

📦 Prerequisites

Java 11+ installed

Maven installed

Chrome browser installed

Git installed

IntelliJ IDEA 



🔧 Setup Instructions

git clone https://github.com/DaveLV24/Team-4.git

cd "project-folder"


Download project dependencies:

mvn clean install



Run all tests:

mvn test


🪛 Troubleshooting
Driver not found:

Make sure chromedriver is located in the correct lib/ folder, and the Hooks.java correctly sets the path.

Glue issues:

If step definitions aren't recognized, check glue = {"stepDefinitions", "hooks"} in your test runner.

Feature file not found:

Ensure the path is correct: features = "src/test/resources/features"



👨‍🏫 Tips for Students


Don’t forget to commit your feature files and step definitions each day:

1.git pull

2.git status

3.git add .

4.git commit -m "added new tests"

5.git push


Git won’t let you push unless there's a commit to push, and Git won’t allow a commit unless a user identity (name & email) is set:

git config user.name "Student Name"

git config user.email "student@example.com"


Use meaningful assertions to test actual functionality.

If test fails, read the console output carefully — most answers are there!