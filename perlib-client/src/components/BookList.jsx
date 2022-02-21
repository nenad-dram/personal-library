import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Box, Button, Container, Typography } from '@mui/material';
import BookDialog from './BookDialog';

function createData(id, title, author, language) {
  return { id, title, author, language };
}

const rows = [
  createData(1, 'Hello World 1', 'Unknown Author 1', 'English'),
  createData(2, 'Hello World 2', 'Unknown Author 2', 'English'),
  createData(3, 'Hello World 3', 'Unknown Author 3', 'English'),
  createData(4, 'Hello World 4', 'Unknown Author 4', 'English'),
  createData(5, 'Hello World 5', 'Unknown Author 5', 'English'),
];

export default function BookList() {
  const [openBook, setOpenBook] = React.useState(false);
  return (
    <Container>
      <Typography variant="h5" align='center'>Book List</Typography>
      <Box>
        <Button onClick={() => setOpenBook(true)}>Add Book</Button>
        <BookDialog open={openBook} onClose={() => setOpenBook(false)} />
      </Box>
      <Box>
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="center">Title</TableCell>
                <TableCell align="center">Author</TableCell>
                <TableCell align="center">Language</TableCell>
                <TableCell align="center">Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row) => (
                <TableRow key={row.id}
                  sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                  <TableCell align="center">{row.title}</TableCell>
                  <TableCell align="center">{row.author}</TableCell>
                  <TableCell align="center">{row.language}</TableCell>
                  <TableCell align="center">
                    <Button onClick={() => setOpenBook(true)}>Edit </Button>
                    <Button>Delete </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Box>
    </Container>
  );
}
