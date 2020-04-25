# Mathematical Expression Evaluator

Provides the following REST API
1. Evaluate mathematical expression
   1. Consumes EvaluationRequest as XML type. It should have User Id and the mathematical expression to be evaluated
   2. Produces EvaluationResult as XML type with the result
   3. Requests with missing details will be responded with corresponding error message in the result object
   4. errorMsg in the result object will be null in successful processing and a corresponding error in case of failures
   
   
2. Retrieve User audit details
   1. Consumes UserAuditRequest as XML type. It should have User Id
   2. Produces UserAuditResult as XML type with the details like evaluation service usage count, most used  operator (and its count) in       the mathematical expressions provided by the user
   3. Requests with missing details will be responded with corresponding error message in the result object
   4. errorMsg in the result object will be null in successful processing and a corresponding error in case of failures
   5. Service request count is tracked by using an AtomicIntegerin UserAuditDetails, which is incremented for each API usage
   6. Similarly in UserAuditDetails, a map Map<Character, AtomicInteger> is used to  track operator and its frequency for each user
   7. Most frequently used operator is determined at runtime in constant time as the map size would be atmost the number of 
      suppported operators at any given time
   
   
   
   
   ## Supported operators
   The evaluation is limited to the oprators  /, *, +, -, ()
   
   ## Caching
   1. User info and UserAuditDetails are cached for easy access
   2. Current implemetation does not have DB persistence
   3. Audit details requests are server by using the data from the cache (This is temporary. Once DB implemetation is done, both DB and cache can co exist)
   
   
   ## Future Scope
   
   ### Application
   1. Support for user sign ups and logins. Registered users can have access to APIs which can evaluate more complex expressions
      and equations with variables and their respective values
   2. User specific stats
   
   ### Functionality    
   1. Add support to additional operators like ^, !
   2. Add support to functions like log, sqrt
   3. Support complex expressions with multiple variables and their respective values
   
   ### DataBase
   1. Have data models to persist and maintain user profiles
   2. Table to store User audit details
   3. DB persistence/updates can be implemented using Executor Framework with fixed thread pool or cached thread pool which can be    determined by looking at the API usage patterns
  
   
   ### Caching
   1. Implemetations like LRU cache and distributed cache and be further analyzed
   
