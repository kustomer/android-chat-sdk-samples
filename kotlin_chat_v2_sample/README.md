# Kustomer Chat v2 Sample App

This sample app demonstrates the login and describe functionality available for [Kustomer Chat 2.0](https://help.kustomer.com/introduction-kustomer-chat-H1xk1Gb8v) in the [Kustomer Android Chat SDK](https://developer.kustomer.com/chat-sdk/v2-Android/docs).

## Project Structure

### SDK Initialization
- `build.gradle(:app)`: This includes dependencies for the Kustomer Chat SDK.
- `CustomApplication.kt`: This is the Application override where the Kustomer SDK is instantiated for the app.
- `AndroidManifest.xml`: This declares the use of `CustomerApplication.kt` in the app

###


### App functionality

The sample app features three screens. Each screen is composed of a `Fragment` and a `ViewModel`. All calls to the Kustomer
SDK are located in `ViewModel` classes.

We've provided more information about each screen below.

#### Login Screen
The Login screen allows a customer to either:

- Log into the app to view an Order History screen (any email/password combination will succeed), or
- Continue to a Guest screen without any order details

After a successful login, the user is logged into Kustomer Chat.

![Login Screen](./screenshots/login_screen.png?raw=true "Login Screen")


#### Order History Screen
The Order History screen:

- Describes a customer based on the email address of the customer
- Shows order details for three orders
    - Each order shows a Get Help button that opens a Kustomer conversations and describes that conversation with the order number           
      * **Note**: The sample code calls `Kustomer.getInstance().describeConversation()` with a conversation attribute named `orderId` and a string value to demonstrate describing a conversation with a [custom attribute](https://developer.kustomer.com/chat-sdk/v2-Android/docs/describe-conversation#use-custom-attributes). This call is expected to fail and show an error toast as-is. To test the `describeConversation` functionality, replace the `orderId` attribute with a custom conversation attribute for your org.
- Creates an in-memory map of the order number to the conversation ID
    - The first tap on Get Help opens a new conversation, but subsequent taps open the existing conversation for that order

![Order History Screen](./screenshots/order_history_screen.png?raw=true "Order History Screen")

#### Guest Screen
The Guest screen shows basic interactions with the Kustomer SDK:

- Open the Kustomer Chat widget based on the default [Chat Widget Experience](https://help.kustomer.com/chat-design-Skgvx4KQf#chat-widget-experience) setting for your Kustomer organization (for example, Live Chat, Knowledge Base, or KNowledge Base + Live Chat).
- Open a new conversation
- Open the Kustomer Chat widget with Live Chat only, regardless of the Chat Widget Experience settings for your organization
- Open the Kustomer Chat widget with Knowledge Base only, regardless of the Chat Widget Experience settings for your organization
- Toggle between light mode and dark mode

![Guest Screen](./screenshots/guest_screen.png?raw=true "Guest Screen")

## Running the project
> To run the project, you'll need [Android Studio 4.2](https://developer.android.com/studio/releases) or higher.

1. First, [clone](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository-from-github/cloning-a-repository) the [`android-chat-sdk-samples`](https://github.com/kustomer/android-chat-sdk-samples) repository.

2. In a root-level `local.properties` file, add a [Kustomer API key with `org.tracking` level permissions](https://developer.kustomer.com/chat-sdk/v2-Android/docs/authentication#step-1-generate-a-new-kustomer-api-key) as `apiKey=yourAPIkey`. Example: `apiKey=123456789abcdefg`.

![local.properties API Key](./screenshots/api_key_example.png?raw=true "local.properties API Key")

3. Next, test the login functionality. You'll need to first generate a valid JWT ([JSON Web Token](https://jwt.io/introduction)) with a [secret key](https://developer.kustomer.com/chat-sdk/v2-Android/docs/authentication#step-2-generate-a-secret-key) for your Kustomer organization, and then paste the secret key into the `jwt` value in `LoginViewModel.kt`. To generat a valid JWT for your organization:
    1. Go to the JWT generator at https://jwt.io/.
    2. In the PAYLOAD: DATA section, enter the key-value pairs for either `email` or `externalId`, and ensure that the `iat` was created within the last 15 minutes.
       * **Note**: You can replace the `iat` value with the current Unix Epoch Time calculated with a free tool like [EpochConverter](https://www.epochconverter.com/) or with a language of your choice.
    3. In the VERIFY SIGNATURE section, replace `your-256-bit-secret` with the secret key you generated for your Kustomer organization from `https://{your-org}.api.kustomerapp.com/v1/auth/customer/settings` (replace `{your-org}` with the subdomain for your Kustomer organization).
     ![JWT Generator](./screenshots/jwt_generation.png?raw=true "JWT generator")
    4. Run the app on your emulator or test device. You can now use the screens described above to test the Chat SDK functionality.
    5. If you are using this sample to test push notifications, you must replace `google-services.json` with a copy of a valid
          `google-services.json` file and change the value of `"package_name"` to `"com.example.kotlin_chat_v2_sample"`. If not testing
          push notifications, you can omit this step.
