//new component example:
class Car extends React.Component {
  constructor() {
    super();
   this.state = {
      brand: "Ford",
      model: "Mustang",
      color: "red",
      year: 1964
    };
  }
  render() {
    return <h2>I am a {this.state.color} Car!</h2>;
  }
}
