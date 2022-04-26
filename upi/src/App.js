import logo from './logo.svg';
import './App.css';
import CourseCard from './components/CourseCard'

const App = () => {
  return (
    <section className="card-list">
      <CourseCard
        courseName="Complete React Native 
        Mobile App developer - Build 10 apps"
        courseThumbnail="https://Link to Image"
        courseDetails="2 Free + 92 Paid"
        coursePrice="2,999"
        courseDiscountedPrice="199"
        courseDiscount="93"
      />
    </section>
  );
}


export default App;
