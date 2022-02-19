import * as React from 'react';
import LoginDialog from './LoginDialog';

function Header() {
    const [openLogin, setOpenLogin] = React.useState(false);

    return (
        <div>
            <header>
                <nav className="navbar-expand-md navbar-dark bg-dark" style={{ height: "3rem", paddingTop: "0.25rem" }}>
                    <div>
                        <span className="navbar-brand" style={{ marginLeft: "1rem" }}>Book Management App</span>
                        <button className="btn btn-primary float-end" style={{ marginRight: "1rem" }} onClick={() => setOpenLogin(true)}>Login</button>
                        <LoginDialog open={openLogin} onClose={() => setOpenLogin(false)} />
                    </div>
                </nav>
            </header>
        </div>
    );
}
export default Header;