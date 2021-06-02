# Kustomer Advanced Example App

This example app demonstrates how to use Kustomer's Login and Describe functionality.

## Project Structure

    .
    ├── manifests
        ├── AndroidManifest.xml  # App manifest file where Application class is defined
    ├── java/com.example.kustomerloginanddescribe
    │   ├── ui.home
    │       ├── itemviews  # Custom views for the example app
    │       ├── HomepageFragment.kt   # A fragment to launch and interact with the Kustomer SDK
    │       ├── HomepageViewModel.kt  # ViewModel that contains most of the calls to the Kustomer SDK
    │   ├── utils                     # Utils and miscellaneous code for the example app
    │   ├── KustomerApplication.kt    # Custom application class where KustomerSDK is initialized
    │   ├── MainActivity.kt           # Entry point to the example app
    └── ...

## Running the project
1. Clone this repository
2. In a root-level `local.properties` file, add your Kustomer API key as below. This API key must
have `org.tracking` level permissions.
`apiKey = {your API key}`
2. In a root-level `local.properties` file, add your Organization's secret key as below. This is
used to sign JWT tokens to login users.
`jwtSecret = {your secret key}`
Your org secret key is available from https://{your-org}.api.kustomerapp.com/v1/auth/customer/settings
