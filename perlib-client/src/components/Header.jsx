function Header() {
    return (
        <div>
            <header>
                <nav className="navbar-expand-md navbar-dark bg-dark" style={{ height: "3rem", paddingTop: "0.25rem" }}>
                    <div>
                        <span className="navbar-brand" style={{ marginLeft: "1rem" }}>Book Management App</span>
                        <button className="btn btn-primary float-end" style={{ marginRight: "1rem" }}>Login</button>
                    </div>
                </nav>
            </header>
        </div>
    );
}
export default Header;