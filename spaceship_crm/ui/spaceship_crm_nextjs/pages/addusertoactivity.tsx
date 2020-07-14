/*
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
import React from 'react';
import RestService from "../services/RestService";
import {Activity, User} from "../types/Types";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Autocomplete from '@material-ui/lab/Autocomplete';
import Router from 'next/router';

export default function () {
    const [user, setUser] = React.useState<number>();
    const [activity, setActivity] = React.useState<number>();

    const [users, setUsers] = React.useState<User[]>();
    const [activities, setActivities] = React.useState<Activity[]>();

    function getUsers() {
        RestService.getAllUsers().then(users => setUsers(users));
    }

    function getActivities() {
        RestService.getAllActivities().then(activities => setActivities(activities));
    }

    React.useEffect(() => {
        getUsers();
        getActivities();
    }, []);

    const add = async () => {
        RestService
            .addUserToActivity(activity!, user!)
            .then(res => Router.push('/activities'))
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
                <Autocomplete
                    id="activities"
                    options={activities!}
                    getOptionLabel={(activity: Activity) => activity.name}
                    style={{ width: 300 }}
                    onChange={(event, value) => setActivity(value!.id)}
                    renderInput={(params) => <TextField {...params} label="Combo box" variant="outlined" />}
                />
                <Autocomplete
                    id="users"
                    options={users!}
                    getOptionLabel={(user: User) => user.firstName + ' ' + user.lastName}
                    style={{ width: 300 }}
                    onChange={(event, value) => setUser(value!.id)}
                    renderInput={(params) => <TextField {...params} label="Combo box" variant="outlined" />}
                />
                <Button onClick={add}>
                    Save
                </Button>
            </Grid>
        </Grid>
    );
}