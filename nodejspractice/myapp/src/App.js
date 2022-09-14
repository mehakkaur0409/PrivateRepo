import { useState, useMemo } from "react";
import "./App.css";

import React, {Suspense } from "react"
import { Waiting } from './waiting';
const Text = React.lazy(() => import('./text'));
const Buttons = React.lazy(() => import('./button'));

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
        <Suspense fallback={<Waiting />}>
            {tab === "text" ? <Text /> : <Button />}
            <a href="#" onClick={_ => {
                if(tab == "text") setTab("buttons")
                else setTab("text")
            }}>
                Toggle
            </a>
        </Suspense>
    </div>
    
  );
}
const doubleMyCount = (count) => {
  console.log("Big expensive function called");
  for (let i = 0; i < 1000000000; i++) {}
  return count * 2;
};
export default App;
