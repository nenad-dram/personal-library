import * as React from 'react';
import BookDialog from './BookDialog';

function BookList() {
    const [openBook, setOpenBook] = React.useState(false);

    return (
        <div>
            <h2 className="text-center">Book List</h2>
            <div>
                <button className="btn btn-primary" onClick={() => setOpenBook(true)}>Add Book</button>
                <BookDialog open={openBook} onClose={() => setOpenBook(false)} />
            </div>
            <br></br>
            <div className="row text-center">
                <table className="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Language</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody className="align-middle">
                        <tr>
                            <td>Hello World</td>
                            <td>Unknown Author</td>
                            <td>English</td>
                            <td>
                                <button className="btn btn-info" onClick={() => setOpenBook(true)}>Edit </button>
                                <button style={{ marginLeft: "1rem" }} className="btn btn-danger">Delete </button>
                            </td>
                        </tr>
                    </tbody>
                </table>

            </div>

        </div>
    );
}

export default BookList;