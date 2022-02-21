import './App.css';
import BookList from './components/BookList';
import Header from './components/Header';
import Footer from './components/Footer';
import { Box } from '@mui/system';

function App() {
  return (
    <Box>
      <Header />
      <BookList />
      <Footer />
    </Box>
  );
}

export default App;
