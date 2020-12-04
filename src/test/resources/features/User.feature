#lang: en
Feature: Everything around the users CRUD

  Scenario: Request user info by mail
    Given The user "u1@gmail.com"
    When  Request the user info
    Then The info of the user is correct
      | id         | 1                   |
      | email      | test_root@gmail.com |
      | first_name | Raiz                |
      | last_name  | Rodriguez           |
      | country    | España              |
      | city       | Madrid              |
      | company    | Verdecora           |
      | enabled    | true                |
      | verified   | true                |
      | role       | ROLE_ROOT           |