import './App.css';
import BookList from './components/BookList';
import Header from './components/Header';
import Footer from './components/Footer';

function App() {
  return (
    <div>
      <Header />
      <div className="container">
        <BookList></BookList>
      </div>
      <Footer />
    </div>
  );
}

export default App;
