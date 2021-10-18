//* first class functions-> function in js can be used as variables meaning it can be passed to a function param
//* function expression-> function can be assigned to a variable and it is anonymous function. variable () -> will invoke the method.

//normal method declaration
function printMehak() {
  console.log("mehak");
}
//normal method invoker
printMehak();

//!-----------------------------------
//FCF
 function methodAcceptingFn(fn){
   fn();

 }
 //FCF method invoker
 methodAcceptingFn(printMehak);

 //!-----------------------------------

 //function expression
 var r=function(){
   console.log('function expression method called');
 }
  //function expression method invoker
  r();