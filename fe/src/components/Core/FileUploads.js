import React, { useCallback, useState, useEffect } from 'react';
import { fileRegister, fileSelector } from '../../state/reducer/file.reducer';
import { useDispatch, useSelector } from 'react-redux';
import { deleteFile } from '../../state/reducer/file.reducer';
import Swal from 'sweetalert2';

const sweetalert = (icon, title, text, footer) => {
    Swal.fire({
        icon: icon,
        title: title,
        text: text,
        footer: footer,
    });
};

const FileUploads = ({ image }) => {
    const dispatch = useDispatch();

    const fileList = useSelector(fileSelector).fileList;

    const [remove, setRemove] = useState('');

    useEffect(() => {}, [setRemove]);

    const handleSelectedImgs = useCallback((e) => {
        if (image !== null) {
            e.preventDefault();

            const formData = new FormData();
            const imgFile = e.target.files;

            for (let i = 0; i < imgFile.length; i++) {
                formData.append('uploadFiles', imgFile[i]);

                if (imgFile[i].name.includes('video')) {
                    const removeMP4 = imgFile[i].name.slice(
                        0,
                        imgFile[i].name.length - 4
                    );
                    setRemove(removeMP4);
                }
            }
            dispatch(fileRegister(formData));
        } else {
            Swal.fire({
                icon: 'error',
                title: '프로필 사진이 없습니다!',
                text: '프로필 사진을 등록해주세요',
            });
        }
    });

    return (
        <>
            <div id="dropzone">
                <label
                    htmlFor="fileUpload"
                    className="mb-0 font-size-4 text-smoke"
                ></label>
                {fileList.map((file) => {
                    return (
                        <div className="thumnails-container">
                            {file.photoType === true ? (
                                <img
                                    onClick={(e) => {
                                        e.preventDefault();
                                        console.log(file.uuid);
                                        dispatch(deleteFile(file.uuid));
                                    }}
                                    className="thumnails-size"
                                    src={`http://localhost:8080/files/display?fileName=s_${file.uuid}_${file.fileName}`}
                                />
                            ) : (
                                <img
                                    onClick={(e) => {
                                        e.preventDefault();
                                        console.log(file.uuid);
                                        dispatch(deleteFile(file.uuid));
                                    }}
                                    className="thumnails-size"
                                    src={`http://localhost:8080/files/display?fileName=${file.uuid}_${remove}.jpg`}
                                />
                            )}
                            <p className="thumnails-delBtn">삭제하기</p>
                        </div>
                    );
                })}
                <input
                    multiple="multiple"
                    accept="image/*, video/*"
                    id="fileUpload"
                    type="file"
                    name="filename[]"
                    onChange={handleSelectedImgs}
                />
            </div>
        </>
    );
};

export default FileUploads;
