function BookList() {
    return (
        <div>
            <h2 className="text-center">Book List</h2>
            <div>
                <button className="btn btn-primary">Add Book</button>
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
                                <button className="btn btn-info">Edit </button>
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