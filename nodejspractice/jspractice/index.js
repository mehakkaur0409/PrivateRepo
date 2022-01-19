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

// // block scope
// var a = 100;
// let b = 5; //but b in script scope
// {
//   var a = 10; //a is in global scope
//   let b = 100; //b is in block scope, can't be accessed outside the block
//   const c = 20;
//   console.log(b);
// }
// console.log(b); //this shadowed b inside block

//let & const

const z = 10;
function m() {
  const z = 5;
  console.log({ z });
}
console.log(m());
console.log({ z });

//closure copy paste example:
function init() {
  var name = "Mozilla"; // name is a local variable created by init
  function displayName() {
    // displayName() is the inner function, a closure
    alert(name); // use variable declared in the parent function
  }
  displayName();
}
init();

//testing commit behaviour

//simple set time out examlple:
let a = 1;
let b = 2;
console.log("Sum =", a + b);

setTimeout(() => {
  console.log(a, b);
}, 1000);

//
let timerId = setInterval(() => alert("tick"), 2000);

// after 5 seconds stop
setTimeout(() => {
  clearInterval(timerId);
  alert("stop");
}, 5000);

//nested settimeout
let timerId = setTimeout(function tick() {
  alert("tick");
  timerId = setTimeout(tick, 2000); // (*)
}, 2000);

//zero delay timeout ??
setTimeout(() => alert("World"));

alert("Hello");

// empty function syntax
() => {};
