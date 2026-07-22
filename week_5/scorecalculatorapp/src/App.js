import './App.css';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div className="App">
      <CalculateScore Name="John Doe" School="ABC High School" Total={380} goal="Pass" />
    </div>
  );
}

export default App;
