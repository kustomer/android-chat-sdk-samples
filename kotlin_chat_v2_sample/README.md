# Kustomer Chat v2 Sample App

This example app demonstrates how to use Kustomer's Login and Describe functionality.

## Project Structure

### SDK Initialization
- `build.gradle(:app)` - includes the dependency for the Kustomer Chat SDK
- `CustomApplication.kt` - the app's Application override where the Kustomer SDK is instantiated
- `AndroidManifest.xml` - declares the use of `CustomerApplication` in the app

### App functionality

The app has three screens, each composed of a Fragment and ViewModel. All calls to the Kustomer
SDK can be found in ViewModel classes.

#### Login -
- Allows logging in to the app to view an order history (any email/password combo will succeed),
or continuing as guest. On successful login, the user is also logged in to Kustomer chat.Advanced
![Login Screen](./screenshots/login_screen.png?raw=true "Login Screen")


#### Order History -
- Describes the customer with their email address

- Shows order details for three orders. Each order has a "Get Help" button that will
open a Kustomer conversation and describe that conversation with the order number.

- Here we also create an in-memory map of order number to conversation ID, so the first tap on "Get help"
will open a new conversation, but subsequent taps will open the existing conversation for that order
![Order History Screen](./screenshots/order_history_screen.png?raw=true "Order History Screen")

#### Guest - Shows some basic interactions with the Kustomer SDK
- Open the Kustomer Chat widget with your organization's default settings for whether to show the
knowledge base, chat, or both

- Open a new conversation

- Open the Kustomer Chat widget with chat only, regardless of your organization's settings

- Open the Kustomer Chat widget with knowledge base only, regardless of your organization's settings

- Toggle between light mode and dark mode
![Guest Screen](./screenshots/guest_screen.png?raw=true "Guest Screen")

## Running the project
1. Clone this repository
2. In a root-level `local.properties` file, add your Kustomer API key as below. This API key must
have `org.tracking` level permissions.
`apiKey = {your API key}`
3. To test the login functionality, generate a valid JWT with our org's secret key and paste it in to
the `jwt` value in `LoginViewModel.kt`. For testing, you can generate a JWT by doing the following:
    - Go to https://jwt.io/
    - Fill in the `payload` with either an `email` or `externalId` and ensure that the `iat` was created
    within the last 15 minutes. You can replace the `iat` with the current Unix Epoc Time calculated by using the
    language of your choice or a tool like https://www.epochconverter.com/
    - Paste in your org's secret key from `https://{your-org}.kustomerapp.com/v1/auth/customer/settings`
![JWT Generator](./screenshots/jwt_generation.png?raw=true "JWT Generator")


