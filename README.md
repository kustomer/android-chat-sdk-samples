# Kustomer Advanced Example App

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

#### Order History -
- Describes the customer with their email address

- Shows order details for three orders. Each order has a "Get Help" button that will
open a Kustomer conversation and describe that conversation with the order number.

- Here we also create an in-memory map of order number to conversation ID, so the first tap on "Get help"
will open a new conversation, but subsequent taps will open the existing conversation for that order

#### Guest - Shows some basic interactions with the Kustomer SDK
- Open the Kustomer Chat widget with your organization's default settings for whether to show the
knowledge base, chat, or both

- Open a new conversation

- Open the Kustomer Chat widget with chat only, regardless of your organization's settings

- Open the Kustomer Chat widget with knowledge base only, regardless of your organization's settings

- Toggle between light mode and dark mode

## Running the project
1. Clone this repository
2. In a root-level `local.properties` file, add your Kustomer API key as below. This API key must
have `org.tracking` level permissions.
`apiKey = {your API key}`
3. To test the login functionality, generate a JWT (more details noted in the comments for LoginViewModel.onLoginSucceeded)
