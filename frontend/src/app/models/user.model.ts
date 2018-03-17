export class User {
    id: string;
    username: string;
    password: string;

    constructor(username: string, password: string) {
        this.id = null;
        this.username = username;
        this.password = password;
    }
}
