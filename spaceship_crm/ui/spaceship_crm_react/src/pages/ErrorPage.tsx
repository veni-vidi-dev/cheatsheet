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
import Typography from "@material-ui/core/Typography";

export default function ErrorPage() {

    return (
        <Grid
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justify="center"
            style={{ minHeight: '100vh' }}
        >
            <Typography>Error occurred</Typography>
        </Grid>
    );
}