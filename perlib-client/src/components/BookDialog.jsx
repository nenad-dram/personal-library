import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';

function BookDialog(props) {
    return (
        <div>
            <Dialog open={props.open}>
                <DialogTitle>Book Data</DialogTitle>
                <DialogContent>
                    <FormControl variant="standard">
                        <TextField id="title" label="Title" type="text" fullWidth variant="standard" />
                        <TextField id="author" label="Author" type="text" fullWidth variant="standard" />
                        <TextField select label="Language" id="language" value="" variant="standard" fullWidth>
                            <MenuItem value=""></MenuItem>
                            <MenuItem value={"English"}>English</MenuItem>
                            <MenuItem value={"Serbian"}>Serbian</MenuItem>
                        </TextField>
                    </FormControl>
                </DialogContent>
                <DialogActions>
                    <Button onClick={props.onClose}>Cancel</Button>
                    <Button onClick={props.onSubmit}>Save</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default BookDialog;