// getname(5);
// console.log(getname());

// //var x = 7;
// function getname(r) {
//   console.log("--->", r);
//   console.log("Javascript");
// }

// //Funtion invoke
// console.log(x);
// getname();

//hoisting of three functions: meaning calling fns before they are declared.

// console.log(getname1);
// console.log(x);
// console.log(r);
// function getname1() {
//   var x = 5;
//   console.log("Java");
// }
// var x = function () {
//   var x = 5;
//   console.log("Jvascript");
// };
// var r = () => {
//   var x = 5;
// };

//Scope chain

// function childFunctionToGlobalContext() {
//   //hence can access m
//   var z = 10;
//   console.log(m);
//   childFunctionToAboveFunction();
//   function childFunctionToAboveFunction() {
//     console.log(z);
//   }

//   var x = () => {
//     console.log(m);
//   };
//   x();
//}
// var m = 5;
// childFunctionToGlobalContext();
// var m = 8;

// block scope
var a = 100;
let b = 5; //but b in script scope
{
  var a = 10; //a is in global scope
  let b = 100; //b is in block scope, can't be accessed outside the block
  const c = 20;
}
console.log(b);
