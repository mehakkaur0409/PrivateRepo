import logo from './logo.svg';
import './App.css';

function App() {
  const { data, isValidating, mutate } = useSWR(
    'https://api.github.com/users/toruticas',
  )

  return (
    <div className="ProfileData">
      ...
      
      {isValidating && (
        <div className="spinner">
          <SpinnerLoader />
        </div>
      )}

      <button onClick={() => mutate()} disabled={isValidating}>
        Revalidate
      </button>
    </div>
  )
}

export default App;
