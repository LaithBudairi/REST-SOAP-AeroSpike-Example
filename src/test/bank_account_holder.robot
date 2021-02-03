*** Settings ***
Library    Selenium2Library
Library    REST    http://localhost:8080/api
*** Variables ***
${browser}    chrome
${url}    https://google.com
${version}    /v1
${accountHolder}    /accountHolder/

*** Keywords ***
Go to Google
    Open Browser    ${url}    ${browser}

*** Test Cases ***
Test Case Example
    Go to Google
    Maximize Browser Window
    Close Browser

Get Account Holder
    GET    ${version}${accountHolder}1
    Output    response body
    Integer    response status    200
    String    response body id    1
    String    response body firstName    Laith
    String    response body lastName    Budairi
    Number    response body balance    -25.5

Get AlL Account Holders
    GET    ${version}${accountHolder}
    Integer    response status    200

Post AccountHolder
    POST    ${version}${accountHolder}     {"id": "3", "firstName": "Automation", "lastName": "Test", "balance": 500.0 }
    Integer     response status     201