# Mustang Helper - Alexa Skill

## Developer Requirements
1. Maven should be downloaded on your machine. If you don't have maven, download it by running `sudo apt-get install maven` (Ubuntu or Windows Ubuntu), `sudo yum install maven` (Linux), or `brew install maven` (Mac).
2. You should probably have an IDE of some sort.
3. As a developer, you should have access to the Lambda function and Alexa skill.

## Uploading to AWS Lambda
Compile the program by running `mvn clean install` in the base project directory. To upload to Lambda:
1. Run `mvn package shade:shade` to generate an uber-jar of the program. The correct file should be under `target/mustang-helper-1.0.jar`
2. Navigate to the Lambda function page and upload the above .jar file
3. Ensure that the Lambda handler class is set to `com.cpe.musty.MustangHelperSpeechletRequestStreamHandler`
4. Press the Save button at the top of the Lambda page

## Updating Alexa Skill
To update the Alexa skill to point to the Lambda function:
1. Copy the Lambda ARN, listed at the top right of the page, from the above Lambda function
2. Navigate to the Cal Poly Mustang Assistant skill
3. Under Configuration, paste the new Lambda ARN.
4. At the bottom of the page, press Save

## Adding Intent Handlers
First off, a single intent should correspond to one action that the skill can make. This means that "Reserve a room in the library." and "Reserve a book from the library." should be *separate* intents.

* Create a new implementation of the IntentHandler interface.
* The `handle()` method takes in an Intent object, grabs any relevant Slots, and creates a SpeechletResponse object to return to the device.
* If an intent handler finds an invalid Intent, they should call out to `HelpIntentHandler` to have the device use the default help message.
* Any custom intents should catch any Exception thrown in their code and pass the error message to the Help Intent Handler, i.e.:
```
try {
   // Workflow logic
} catch (Exception e) {
   HelpIntentHandler helpHandler = HelpIntentHandler.getInstance();
   helpHandler.setSpeechOutput(e.getMessage());
   return helpHandler.handle(intent);
}
```
