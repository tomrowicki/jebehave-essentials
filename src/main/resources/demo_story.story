Narrative: How to invoice customers properly.

Scenario: As a new client I want to be granted discount and get invoiced in accordance with the service package I subscribe to.
Given a client with the id 123, who just subscribed to STARTER service
And he was provided with discount NEWCOMER_DISCOUNT
When the client gets invoiced
Then they should pay 20.99 EUR in 30 days
And the discount associated with their account should be NONE

Scenario: Having no discount, depending on the service I need to be invoiced for different amounts
Given a client with the id <id>, who just subscribed to <tier> service
And he was provided with discount NONE
When the client gets invoiced
Then they should pay <amount>

Examples:
|id     |tier         |amount |
|234    |PRO          |40.99  |
|345    |ULTRAVIP     |99.99  |
