
//OBJECT LITERAL->KEY VALUE PAIR SEPARATED BY COMMA
var obj={
    firstname:'mehak',
    lastname:'kaur',
    age:{
        min:1,
        max:99
    },
    //function expression
    greet:function(){
console.log('heya mehak');
    }

}

//below are by dot operator
console.log(obj.firstname);
console.log(obj['firstname']);

console.log(obj.age);
console.log(obj.age.min);
console.log(obj.age['min']);

console.log(obj.greet);
console.log(obj.greet());

//below is by []
