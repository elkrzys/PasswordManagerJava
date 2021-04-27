# MVC Password Manager applications in Java
## The same model used in two separate with Swing views and JavaFX views.

Application built to learn Java and idea of MVC.
First version is implemented with *Swing*.
*JavaFX* is second, better implementation of MVC pattern.

Application allow users to sign up and store list of different accounts with details.

Main features:
- adding list of accounts with details
  - platform name, account name (email), description and password
- editing account from list
- deleting account from list
- saving changes (locally)

Data of each user is stored in a separate .json file.

*It's not a safe application - there is no cryptography implemented.*

To read the **documentation** you can download *apidocs* directory of each version and open *index.html* file.
- Swing: https://github.com/elkrzys/password-manager-java/tree/main/JavaSwing/PasswordManager/target/site/apidocs
- JavaFX: https://github.com/elkrzys/password-manager-java/tree/main/JavaFX/PasswordManagerFX/target/site/apidocs

### Both version have unit tests for application models written with JUnit.
