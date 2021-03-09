# Random Number Generator
Simple Application which generating random Integer numbers made

## How to run

#### From JAR:
- Compile: `mvn clean install`
- Run (from target): `java -jar random-number-generator-1.0.0-SNAPSHOT.jar --random-number-generator.scheduled.generate-every-milliseconds='<HOW_OFFEN_GENERATE_NUMBER>' --random-number-generator.range.min='<MIN_RANGE_VALUE>' --random-number-generator.range.max='<MAX_RANGE_VALUE>' --random-number-generator.operator='<ARITHMETIC_OPERATOR>'`

#### From Maven runner
- Execute runner: `mvn spring-boot:run "-Dspring-boot.run.arguments=--random-number-generator.scheduled.generate-every-milliseconds=<HOW_OFFEN_GENERATE_NUMBER> --random-number-generator.range.min=<MIN_RANGE_VALUE> --random-number-generator.range.max=<MAX_RANGE_VALUE> --random-number-generator.operator=<ARITHMETIC_OPERATOR>"`

#### *when above run arguments mean 
(if you don't set values in these parameters, application set default value):

`<MIN_RANGE_VALUE>` - parameter sets lower value of the range of numbers from which the number is to be generated. Minimum value must be an integer in the [-1000000000,1000000000] interval, `default value = 1`

`<MAX_RANGE_VALUE>` - parameter sets upper value of the range of numbers from which the number is to be generated. Minimum value must be an integer in the [-1000000000,1000000000] interval, `default value = 1000000000`

`<OPERATOR>` - parameter that sets how the numbers are to be concatenated. Supported values for this parameter: + - / * `default value = +`


## How to use
At http://localhost:8081/random-number-generator/api/generate application provides a REST GET endpoint that returns generated random values