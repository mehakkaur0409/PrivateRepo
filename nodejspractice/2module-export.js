//* module are boxed meaning other module can't access it unless you export it and import it in using module.
var m = function () {
  console.log("module function called by exporting it.");
};

export default m;

export function directExportFunction() {
  console.log("direct exported function");
}
