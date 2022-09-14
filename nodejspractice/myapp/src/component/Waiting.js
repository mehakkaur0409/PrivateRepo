import React from "react";
import PropTypes from "prop-types";
import "./Button.css";

export default class Button extends React.Component {

  render() {
    
    return (
      <div className="abc">
        <button onClick={this.handleClick}>hi i am waiting........</button>
      </div>
    );
  }
}
