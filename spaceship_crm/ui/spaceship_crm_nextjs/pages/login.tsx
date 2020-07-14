/*
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
import React from 'react';
import { Backdrop } from '@material-ui/core';
import CircularProgress from '@material-ui/core/CircularProgress';
import RestService from "../services/RestService";
import {User, UserBasic} from "../types/Types";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Router from 'next/router';

export default function () {
    const [username, setUsername] = React.useState<string>('');
    const [password, setPassword] = React.useState<string>('');
    const [showBackdrop, setShowBackdrop] = React.useState<boolean>(true);

    async function redirectIfLoggedIn() {
        setShowBackdrop(false);
    }

    React.useEffect(() => {
        redirectIfLoggedIn();
    }, []);

    const login = async () => {
        setShowBackdrop(true);
        const userBasic = { username: username, password: password };
        console.log({userBasic});
        RestService
            .login({ username: username, password: password })
            .then((user: User) => {
                Router.push('/activities')
            })
            .catch(error => setShowBackdrop(false) );
    }

    return (
        <Grid
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justify="center"
            style={{ minHeight: '100vh' }}
        >
            <Grid item xs={4}>
                <TextField
                    id={"login"}
                    label="Username"
                    variant="outlined"
                    value={username}
                    helperText={'Enter username'}
                    onChange={(event) => { setUsername(event.target.value) }}
                />
                <TextField
                    id={"login"}
                    label="Password"
                    variant="outlined"
                    type={'password'}
                    value={password}
                    helperText={'Enter password'}
                    onChange={(event) => { setPassword(event.target.value) }}
                />
                <Button onClick={login}>
                    Save
                </Button>
            </Grid>
            <Backdrop open={showBackdrop}>
                <CircularProgress color="inherit" />
            </Backdrop>
        </Grid>
    );
}