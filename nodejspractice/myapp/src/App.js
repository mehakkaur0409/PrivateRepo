import { useState, useMemo } from "react";
import "./App.css";
function App() {
  const [color, setColor] = useState("#fffaaa");
  const [count, setCount] = useState(0);
  //const twiceCount = doubleMyCount(count);
  const twiceCount = useMemo(() => {
    return doubleMyCount(count);
  }, [count]);
  const generateRandomColor = () => {
    setColor("#" + Math.floor(Math.random() * 16777215).toString(16));
  };
  return (
    <div className="App">
      <div className="demo-container">
        <button
          style={{ backgroundColor: color }}
          onClick={generateRandomColor}
        >
          Click to change my color!
        </button>
        <button onClick={() => setCount((prevCount) => prevCount + 1)}>
          I have been clicked {count} times
        </button>
        My double count = {twiceCount}
      </div>
    </div>
  );
}
const doubleMyCount = (count) => {
  console.log("Big expensive function called");
  for (let i = 0; i < 1000000000; i++) {}
  return count * 2;
};
export default App;
