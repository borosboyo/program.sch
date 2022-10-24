import {
    Box,
    Flex,
    Link,
    Button,
    useDisclosure,
    useColorModeValue,
    Stack,
    useColorMode,
    Heading,
} from '@chakra-ui/react';
import {InfoIcon, MoonIcon, PlusSquareIcon, SunIcon} from '@chakra-ui/icons';
import LoginControl from "../login/LoginControl";
import {useHistory} from "react-router-dom";

export default function AppNavbar() {
    const {colorMode, toggleColorMode} = useColorMode();

    return (
        <>
            <Box bg={useColorModeValue('gray.100', 'gray.900')} px={4}>
                <Flex h={16} alignItems={'center'} justifyContent={'space-between'}>
                    <Flex alignItems={'center'}>
                        <Stack direction={'row'} spacing={2}>
                            <Heading
                                textShadow='3px 3px #808080'
                            ><Link style={{textDecoration: 'none'}} href="/">PROGRAM.SCH</Link></Heading>
                            <Button variant={'ghost'} rightIcon={<PlusSquareIcon/>}><Link
                                style={{textDecoration: 'none'}} href="/event/new">Új program</Link></Button>
                            <Button variant={'ghost'} rightIcon={<InfoIcon/>}><Link style={{textDecoration: 'none'}}
                                                                                    href="http://localhost:8080/swagger-ui/index.html">API</Link></Button>
                        </Stack>
                    </Flex>
                    <Flex alignItems={'center'}>
                        <Stack direction={'row'} spacing={2}>
                            <Button onClick={toggleColorMode}>
                                {colorMode === 'light' ? <MoonIcon/> : <SunIcon/>}
                            </Button>
                            <LoginControl/>
                        </Stack>
                    </Flex>
                </Flex>
            </Box>
        </>
    );
}
