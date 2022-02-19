import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import FormControl from '@mui/material/FormControl';

function LoginDialog(props) {
    return (
        <div>
            <Dialog open={props.open}>
                <DialogTitle>Login Data</DialogTitle>
                <DialogContent>
                    <FormControl variant="standard">
                        <TextField id="username" label="Username" type="text" fullWidth variant="standard" />
                        <TextField id="password" label="Password" type="password" fullWidth variant="standard" />
                    </FormControl>
                </DialogContent>
                <DialogActions>
                    <Button onClick={props.onClose}>Cancel</Button>
                    <Button onClick={props.onSubmit}>Login</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default LoginDialog;